package priadko.android.hearthstonecards.base.base_fragment.mvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import priadko.android.hearthstonecards.base.base_mvp.BasePresenter;
import priadko.android.hearthstonecards.base.base_mvp.BaseView;
import priadko.android.hearthstonecards.base.base_mvp.PresenterManager;
import priadko.android.hearthstonecards.base.constants.BUNDLE;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragComingSoon;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragErrorOK;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragErrorOkRetry;
import priadko.android.hearthstonecards.base.dialog_errors.ERROR_TYPES;

public abstract class FragBaseAbstractMVP <P extends BasePresenter<V>, V extends BaseView> extends Fragment {
    /**
     * @return id of rootLayout
     */
    @LayoutRes
    protected abstract int getRootViewId();

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = PresenterManager.getInstance().getFromCache(getFragmentTag());
        if (presenter == null){
            presenter = getPresenter();
            PresenterManager.getInstance().saveToCache(presenter, getFragmentTag());
            Log.i("Presenter", "created " + getFragmentTag());
        } else {
            Log.i("Presenter", "fromCache " + getFragmentTag());
        }
        presenter.bind(getMVPView());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bind(getMVPView());
    }

    @Override
    public void onPause() {
        presenter.unBind();
        PresenterManager.getInstance().saveToCache(presenter, getFragmentTag());
        super.onPause();
    }

    @Override
    public void onStop() {
        presenter.unBind();
        PresenterManager.getInstance().saveToCache(presenter, getFragmentTag());
        super.onStop();
    }

    @Override
    public void onDetach() {
        presenter.unBind();
        PresenterManager.getInstance().saveToCache(presenter, getFragmentTag());
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getRootViewId(), container, false);
    }

    @NonNull
    protected abstract P getPresenter();

    @NonNull
    protected abstract V getMVPView();

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
        } catch (IllegalStateException ignore) {
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
        } catch (IllegalStateException ignore) {
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
        } catch (IllegalStateException ignore) {
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
