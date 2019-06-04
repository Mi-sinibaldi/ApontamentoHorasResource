package resource.estagio.workload.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import resource.estagio.workload.R;

public class DialogApp {

    public static void showDialogConfirm(String message, boolean status, Context context){

        Dialog dialog = createDialog(context,
                status ? R.layout.activity_check : R.layout.acativity_dialog_error);

        TextView text = dialog.findViewById(status ? R.id.textDialog : R.id.text_dialog_error);
        text.setText(message);
        Button buttonConfirmCheck = dialog.findViewById(R.id.button_dialog_error);
        buttonConfirmCheck.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    public static Dialog createDialog(Context context, int layout) {
        Dialog dialog = new Dialog(context, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
        return dialog;
    }

}
