package resource.estagio.workload.ui.admin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import resource.estagio.workload.R;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.ui.DialogApp;
import resource.estagio.workload.ui.employee.EmployeeFragment;
import resource.estagio.workload.ui.login.LoginActivity;

public class HomeAdminActivity extends AppCompatActivity implements HomeAdminContract.View {

    private HomeAdminContract.Presenter presenter;
    private BottomNavigationView navigation;
    private long backPressedTime;
    private Toast backToast;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        presenter = new HomeAdminPresenter(this);
        navigation = findViewById(R.id.bottom_navigation_admin);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_admin, new EmployeeFragment(this)).commit();
        navigation.setOnNavigationItemSelectedListener( menuItem -> {
            presenter.identifyItemClicked(menuItem);
            return true;
        } );
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_admin, fragment).commit();
    }

    @Override
    public void showDialogChooser() {
        dialog = DialogApp.createDialog(this, R.layout.activity_dialog_chooser);

        Button buttonChosserYes = dialog.findViewById( R.id.button_dialog_chooser_yes );
        Button buttonChosserNo = dialog.findViewById( R.id.buttton_dialog_chooser_no );

        buttonChosserYes.setOnClickListener( v -> {
            App.getPref().clear();
            Intent intent = new Intent(HomeAdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } );
        buttonChosserNo.setOnClickListener( v -> dialog.dismiss() );
    }

    @Override
    public void enableNavigation(boolean key) {
        for (int i = 0; i < navigation.getMenu().size(); i++){
            navigation.getMenu().getItem(i).setEnabled(!key);
        }
    }

}
