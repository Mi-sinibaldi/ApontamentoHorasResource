package resource.estagio.workload.ui.home;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import resource.estagio.workload.DialogActivity;
import resource.estagio.workload.R;
import resource.estagio.workload.ui.point.PointFragment;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayoutHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);;

        loadUi();
        loadFragment(new PointFragment(this));

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.frame_layout_home, fragment)
                .commit();
    }

    private void loadUi() {
        frameLayoutHome = findViewById(R.id.frame_layout_home);
    }
}
