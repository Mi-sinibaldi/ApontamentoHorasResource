package resource.estagio.workload;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TEsteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        getSupportActionBar().hide();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teste_activity,new TimelineFragment())
                .commit();
    }
}
