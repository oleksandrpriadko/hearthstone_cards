package priadko.android.hearthstonecards.base.dialog_errors;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_activity.ActivityBaseCustomResult;
import priadko.android.hearthstonecards.base.base_fragment.DlgFragAbstractOk;
import priadko.android.hearthstonecards.base.constants.BUNDLE;

/**
 * TimVision-Android-W12
 * Oleksandr Priadko
 */
public class DlgFragErrorOK extends DlgFragAbstractOk {

    private ActivityBaseCustomResult targetActivity;
    private int requestCode;

    @Override
    protected String getOKText() {
        return getString(android.R.string.ok);
    }

    @Override
    protected void performOK(DialogInterface dialog, int id) {
        if (targetActivity != null) {
            targetActivity.onCustomActivityResult(
                    null,
                    requestCode,
                    ActivityBaseCustomResult.RESULT_OK);
        } else if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(getTargetRequestCode(),
                    ActivityBaseCustomResult.RESULT_OK,
                    null);
        }
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
        ERROR_TYPES errorType
                = ((ERROR_TYPES) getArguments().getSerializable(BUNDLE.SERIALIZABLE.ERROR_TYPE));
        if (errorType == null) {
            return inflater.inflate(R.layout.dlg_error_server, null, false);
        } else {
            switch (errorType) {
                case LOGIN:
                    return inflater.inflate(R.layout.dlg_error_login, null, false);
                case WRONG_IN_SERVER:
                    return inflater.inflate(R.layout.dlg_error_server, null, false);
                case NO_CONNECTION:
                    return inflater.inflate(R.layout.dlg_error_no_conn, null, false);
                default:
                    return inflater.inflate(R.layout.dlg_error_server, null, false);
            }
        }
    }

    public void setTargetActivity(ActivityBaseCustomResult targetActivity, int requestCode) {
        this.targetActivity = targetActivity;
        this.requestCode = requestCode;
    }
}
