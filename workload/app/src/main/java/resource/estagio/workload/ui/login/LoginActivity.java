package resource.estagio.workload.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.timeline.TEsteActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private EditText usernameView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;
    private Button signInButton;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        loadUI();
        loadActions();
    }

    private void loadUI() {
        usernameView = findViewById(R.id.username);
        passwordView = findViewById(R.id.password);
        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);
        signInButton = findViewById(R.id.sign_in_button);
    }

    private void loadActions() {
//        passwordView.setOnEditorActionListener((textView, id, keyEvent) -> {
//            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
//                presenter.login(
//                        usernameView.getText().toString(), passwordView.getText().toString());
//                return true;
//            }
//            return false;
//        });
//
//
//        signInButton.setOnClickListener(view -> presenter.login(
//                usernameView.getText().toString(), passwordView.getText().toString()));

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, TEsteActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        //REMOVE
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
    }
}

