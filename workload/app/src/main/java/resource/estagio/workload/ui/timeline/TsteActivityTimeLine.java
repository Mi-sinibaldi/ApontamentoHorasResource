package resource.estagio.workload.ui.timeline;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import resource.estagio.workload.R;

public class TsteActivityTimeLine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teste_activity,new TimelineFragment())
                .commit();
    }
}
