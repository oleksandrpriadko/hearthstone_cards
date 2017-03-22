package priadko.android.hearthstonecards.base.base_fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import priadko.android.hearthstonecards.base.constants.BUNDLE;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragComingSoon;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragErrorOK;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragErrorOkRetry;
import priadko.android.hearthstonecards.base.dialog_errors.ERROR_TYPES;

public abstract class FragBaseAbstract extends Fragment {

    /**
     * @return id of rootLayout
     */
    @LayoutRes
    protected abstract int getRootViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getRootViewId(), container, false);
    }

    /**
     * Initialize your views here
     *
     * @param rootView parent view of the fragment
     */
    protected abstract void prepareViews(View rootView);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareViews(view);
    }

    /**
     * @return Fragment tag
     */
    public String getFragmentTag() {
        return this.getClass().getSimpleName();
    }

    protected void showErrorOk(int requestCode, ERROR_TYPES errorType) {
        if (!checkIfAdded()) {
            return;
        }
        DlgFragErrorOK dlgFragErrorOK = new DlgFragErrorOK();
        dlgFragErrorOK.setTargetFragment(this, requestCode);
        dlgFragErrorOK.setArguments(setErrorTypeToBundle(errorType));
        try {
            dlgFragErrorOK.show(getFragmentManager(), dlgFragErrorOK.getDialogTag());
        } catch (IllegalStateException ignore){
            //do not show dlg if saveInstanceState called
        }
    }

    protected void showErrorOk(ERROR_TYPES errorType) {
        if (!checkIfAdded()) {
            return;
        }
        DlgFragErrorOK dlgFragErrorOK = new DlgFragErrorOK();
        dlgFragErrorOK.setArguments(setErrorTypeToBundle(errorType));
        try {
            dlgFragErrorOK.show(getFragmentManager(), dlgFragErrorOK.getDialogTag());
        } catch (IllegalStateException ignore){
            //do not show dlg if saveInstanceState called
        }
    }

    protected void showErrorOkRetry(int requestCode, ERROR_TYPES errorType) {
        if (!checkIfAdded()) {
            return;
        }
        DlgFragErrorOkRetry dlgFragErrorOkRetry = new DlgFragErrorOkRetry();
        dlgFragErrorOkRetry.setTargetFragment(this, requestCode);
        dlgFragErrorOkRetry.setArguments(setErrorTypeToBundle(errorType));
        try {
            dlgFragErrorOkRetry.show(getFragmentManager(), dlgFragErrorOkRetry.getDialogTag());
        } catch (IllegalStateException ignore){
            //do not show dlg if saveInstanceState called
        }
    }

    private Bundle setErrorTypeToBundle(ERROR_TYPES errorType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE.SERIALIZABLE.ERROR_TYPE, errorType);
        return bundle;
    }

    protected void showComingSoon() {
        if (!checkIfAdded()) {
            return;
        }
        DlgFragComingSoon dlgFragComingSoon = new DlgFragComingSoon();
        dlgFragComingSoon.show(getFragmentManager(), dlgFragComingSoon.getDialogTag());
    }

    /**
     * Check if fragment is attached to activity and
     * activity is not finishing
     *
     * @return true - if attached, false - otherwise
     */
    protected boolean checkIfAdded() {
        return !(!this.isAdded() || this.getActivity().isFinishing());
    }
}
