package priadko.android.hearthstonecards.base.base_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

public abstract class DlgFragBase extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog alertDialog = createDialogBuilder().create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    protected AlertDialog.Builder createDialogBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getTitleView() != null) {
            builder.setCustomTitle(getTitleView());
        } else if (!TextUtils.isEmpty(getTitle())) {
            builder.setTitle(getTitle());
        }
        View rootView = getRootView(LayoutInflater.from(getContext()));
        if (rootView != null) {
            builder.setView(rootView);
        }
        return builder;
    }

    protected abstract View getTitleView();

    protected abstract String getTitle();

    protected abstract View getRootView(LayoutInflater inflater);

    public String getDialogTag() {
        return this.getClass().getSimpleName();
    }
}
