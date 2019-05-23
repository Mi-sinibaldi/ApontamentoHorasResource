package resource.estagio.workload.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import resource.estagio.workload.DialogActivity;
import resource.estagio.workload.R;
import resource.estagio.workload.ui.login.LoginActivity;
import resource.estagio.workload.ui.point.PointFragment;
import resource.estagio.workload.ui.timeline.TimelineFragment;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private Dialog dialog;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void identifyItemClicked(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.ic_pointing:
                view.showFragment(new PointFragment(view.Context()));
                break;
            case R.id.ic_history:
                view.showFragment(new TimelineFragment());
                break;
            case R.id.ic_config:
                break;
            case R.id.ic_exit:
                showDialogChooser();
                break;
        }
    }
    public void showDialogChooser() {
        dialog = new Dialog( view.Context(), R.style.CustomAlertDialog );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.activity_dialog_chooser );
        dialog.setCancelable( false );
        dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        dialog.show();

        Button buttonChosserYes = dialog.findViewById( R.id.button_dialog_chooser_yes );
        Button buttonChosserNo = dialog.findViewById( R.id.buttton_dialog_chooser_no );

        buttonChosserYes.setOnClickListener( v -> {
                view.exitDialog();
                Intent intent = new Intent( view.Context() , LoginActivity.class);
               view.Context().startActivity( intent );
        } );
        buttonChosserNo.setOnClickListener( v -> dialog.dismiss() );
    }

}
