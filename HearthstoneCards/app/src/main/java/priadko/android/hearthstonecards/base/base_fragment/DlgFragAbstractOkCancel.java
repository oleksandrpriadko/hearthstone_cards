package priadko.android.hearthstonecards.base.base_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

public abstract class DlgFragAbstractOkCancel extends DlgFragBase {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = createDialogBuilder();
        builder.setPositiveButton(getOKText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                performOK(dialogInterface, i);
            }
        })
                .setNegativeButton(getCancelText(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        performCancel(dialogInterface, i);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    protected abstract String getOKText();

    protected abstract String getCancelText();

    protected abstract void performOK(DialogInterface dialog, int id);

    protected abstract void performCancel(DialogInterface dialog, int id);
}
