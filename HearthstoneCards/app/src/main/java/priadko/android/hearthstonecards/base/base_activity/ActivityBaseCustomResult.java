package priadko.android.hearthstonecards.base.base_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_fragment.FragBaseAbstract;
import priadko.android.hearthstonecards.base.base_fragment.mvp.FragBaseAbstractMVP;
import priadko.android.hearthstonecards.base.constants.BUNDLE;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragComingSoon;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragErrorOK;
import priadko.android.hearthstonecards.base.dialog_errors.DlgFragErrorOkRetry;
import priadko.android.hearthstonecards.base.dialog_errors.ERROR_TYPES;


/**
 * TimVision-Android-W12
 * Oleksandr Priadko
 */
public abstract class ActivityBaseCustomResult extends AppCompatActivity {

    protected void replaceFragmentNoStack(FragBaseAbstract fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, fragment)
                .commitAllowingStateLoss(); // we don't need the state
    }

    protected void replaceFragmentNoStack(FragBaseAbstractMVP fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, fragment)
                .commitAllowingStateLoss(); // we don't need the state
    }

    public abstract void onCustomActivityResult(Intent data, int requestCode, int resultCode);

    protected void showErrorOk(int requestCode, ERROR_TYPES errorType) {
        if (!canShowDlg()) {
            return;
        }
        DlgFragErrorOK dlgFragErrorOK = new DlgFragErrorOK();
        dlgFragErrorOK.setTargetActivity(this, requestCode);
        dlgFragErrorOK.setArguments(setErrorTypeToBundle(errorType));
        try {
            dlgFragErrorOK.show(getSupportFragmentManager(), dlgFragErrorOK.getDialogTag());
        } catch (IllegalStateException ignore) {
            //do not show dlg if saveInstanceState called
        }
    }

    protected void showErrorOk(ERROR_TYPES errorType) {
        if (!canShowDlg()) {
            return;
        }
        DlgFragErrorOK dlgFragErrorOK = new DlgFragErrorOK();
        dlgFragErrorOK.setArguments(setErrorTypeToBundle(errorType));
        try {
            dlgFragErrorOK.show(getSupportFragmentManager(), dlgFragErrorOK.getDialogTag());
        } catch (IllegalStateException ignore) {
            //do not show dlg if saveInstanceState called
        }
    }

    protected void showErrorOkRetry(int requestCode, ERROR_TYPES errorType) {
        if (!canShowDlg()) {
            return;
        }
        DlgFragErrorOkRetry dlgFragErrorOkRetry = new DlgFragErrorOkRetry();
        dlgFragErrorOkRetry.setTargetActivity(this, requestCode);
        dlgFragErrorOkRetry.setArguments(setErrorTypeToBundle(errorType));
        try {
            dlgFragErrorOkRetry.show(getSupportFragmentManager(), dlgFragErrorOkRetry.getDialogTag());
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
        if (!canShowDlg()) {
            return;
        }
        DlgFragComingSoon dlgFragComingSoon = new DlgFragComingSoon();
        dlgFragComingSoon.show(getSupportFragmentManager(), dlgFragComingSoon.getDialogTag());
    }

    protected boolean canShowDlg() {
        return !isFinishing();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
