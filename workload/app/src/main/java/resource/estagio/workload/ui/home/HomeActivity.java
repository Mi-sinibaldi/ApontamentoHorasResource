package resource.estagio.workload.ui.home;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import resource.estagio.workload.DialogActivity;
import resource.estagio.workload.R;
import resource.estagio.workload.ui.point.PointFragment;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayoutHome;
    private long backPresssedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        ;

        loadUi();
        loadFragment( new PointFragment( this ) );

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


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN )
                .replace( R.id.frame_layout_home, fragment )
                .commit();
    }

    private void loadUi() {
        frameLayoutHome = findViewById( R.id.frame_layout_home );
    }
}
