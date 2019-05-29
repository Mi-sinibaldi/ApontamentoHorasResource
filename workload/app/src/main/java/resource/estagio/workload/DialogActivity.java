package resource.estagio.workload;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {

    private Button buttonConfirm, buttonChooser, buttonChosserYes,
            buttonChosserNo, buttonConfirmCheck, buttonError;

    Dialog dialog;


   // @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_dialog );
//
//        buttonConfirm = findViewById( R.id.button_project_confirm);
//        buttonChooser = findViewById( R.id.button_escolher );
//        buttonChooser.setOnClickListener( v -> showDialogChooser() );
//    }

//    public void showDialogConfirm() {
//        dialog = new Dialog( this, R.style.CustomAlertDialog );
//        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
//        dialog.setContentView( R.layout.activity_check );
//        dialog.setCancelable( false );
//        dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.
//                SOFT_INPUT_STATE_ALWAYS_HIDDEN );
//        dialog.show();
//
//        buttonConfirmCheck = dialog.findViewById( R.id.button_dialog_check );
//        buttonConfirmCheck.setOnClickListener( v -> dialog.dismiss() );
//    }

    public void showDialogChooser() {
        dialog = new Dialog( this, R.style.CustomAlertDialog );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.activity_dialog_chooser );
        dialog.setCancelable( false );
        dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        dialog.show();

        buttonChosserYes = dialog.findViewById( R.id.button_dialog_chooser_yes );
        buttonChosserNo = dialog.findViewById( R.id.buttton_dialog_chooser_no );

        buttonChosserYes.setOnClickListener( v -> {
//                finish();
//                Intent intent = new Intent( DialogActivity.this , TELA DE LOGIN );
//                startActivity( intent );
        } );
        buttonChosserNo.setOnClickListener( v -> dialog.dismiss() );
    }

    public void showDialogError(){
        dialog = new Dialog( this, R.style.CustomAlertDialog );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.acativity_dialog_error );
        dialog.setCancelable( false );
        dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        dialog.show();
        buttonError = dialog.findViewById( R.id.button_dialog_error);
        buttonError.setOnClickListener( v -> dialog.dismiss() );
    }
}
