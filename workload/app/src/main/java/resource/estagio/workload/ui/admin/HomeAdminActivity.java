package resource.estagio.workload.ui.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import resource.estagio.workload.R;

public class HomeAdminActivity extends AppCompatActivity implements HomeAdminContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);



    }
}
