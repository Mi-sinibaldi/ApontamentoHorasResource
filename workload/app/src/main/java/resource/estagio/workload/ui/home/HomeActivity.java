package resource.estagio.workload.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.point.PointFragment;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private FrameLayout frameLayoutHome;
    private long backPresssedTime;
    private Toast backToast;
    private BottomNavigationView navigation;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        presenter = new HomePresenter(this);
        loadUi();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                presenter.identifyItemClicked(menuItem);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPresssedTime + 20000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText( getBaseContext(),
                    "Pressione novamente para sair", Toast.LENGTH_SHORT );
            backToast.show();
        }
        backPresssedTime = System.currentTimeMillis();
    }

    private void loadUi() {
        frameLayoutHome = findViewById( R.id.frame_layout_home );
        navigation = findViewById(R.id.bottom_navigation_employee);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_home,fragment).commit();
    }

    @Override
    public Context Context() {
        return this;
    }

    @Override
    public void exitDialog() {
        finish();
    }
}
