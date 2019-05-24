package resource.estagio.workload.ui.admin;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.employee.EmployeeFragment;

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
                .replace(R.id.frame_admin, new EmployeeFragment()).commit();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                presenter.identifyItemClicked(menuItem);
                return true;
            }
        });
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_admin, fragment).commit();
    }

    @Override
    public void showDialogChooser() {
        dialog = new Dialog( this, R.style.CustomAlertDialog );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.activity_dialog_chooser );
        dialog.setCancelable( false );
        dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        dialog.show();

        Button buttonChosserYes = dialog.findViewById( R.id.button_dialog_chooser_yes );
        Button buttonChosserNo = dialog.findViewById( R.id.buttton_dialog_chooser_no );

        buttonChosserYes.setOnClickListener( v -> {
            finishAffinity();

        } );
        buttonChosserNo.setOnClickListener( v -> dialog.dismiss() );
    }

    @Override
    public void enableNavigation(boolean key) {
        for (int i = 0; i < navigation.getMenu().size(); i++){
            navigation.getMenu().getItem(i).setEnabled(!key);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 20000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText( getBaseContext(),
                    "Pressione novamente para sair", Toast.LENGTH_SHORT );
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
