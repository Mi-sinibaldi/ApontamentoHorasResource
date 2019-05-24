package resource.estagio.workload.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.point.PointFragment;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private FrameLayout frameLayoutHome;
    private long backPresssedTime;
    private Toast backToast;
    private BottomNavigationView navigation;
    private HomeContract.Presenter presenter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenter(this);
        loadUi();
        showFragment(new PointFragment(this));

        navigation.setOnNavigationItemSelectedListener(menuItem -> {

            presenter.identifyItemClicked(menuItem);
            return true;
        });

    }

    @Override
    public void onBackPressed() {
        if (backPresssedTime + 20000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;

        } else {
            backToast = Toast.makeText(getBaseContext(),
                    "Pressione novamente para sair", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPresssedTime = System.currentTimeMillis();
    }

    private void loadUi() {
        frameLayoutHome = findViewById(R.id.frame_layout_home);
        navigation = findViewById(R.id.bottom_navigation_employee);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_home, fragment).commit();
    }




    @Override
    public void dialog(boolean key) {

        for (int i = 0; i < navigation.getMenu().size(); i++){
            navigation.getMenu().getItem(i).setEnabled(!key);
        }
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
}
