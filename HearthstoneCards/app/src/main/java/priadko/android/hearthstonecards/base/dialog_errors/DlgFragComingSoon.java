package priadko.android.hearthstonecards.base.dialog_errors;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_fragment.DlgFragAbstractOk;

/**
 * TimVision-Android-W12
 * Oleksandr Priadko
 */
public class DlgFragComingSoon extends DlgFragAbstractOk {

    @Override
    protected String getOKText() {
        return getString(android.R.string.ok);
    }

    @Override
    protected void performOK(DialogInterface dialog, int id) {

    }

    @Override
    protected View getTitleView() {
        return null;
    }

    @Override
    protected String getTitle() {
        return "";
    }

    @SuppressLint("InflateParams")
    @Override
    protected View getRootView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.dlg_coming_soon, null, false);
    }
}
