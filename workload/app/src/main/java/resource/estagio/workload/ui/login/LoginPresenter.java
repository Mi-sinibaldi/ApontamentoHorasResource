package resource.estagio.workload.ui.login;

import android.app.Activity;

import resource.estagio.workload.data.repository.AuthRepository;
import resource.estagio.workload.domain.User;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.ui.home.HomeActivity;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        final User user = new User(username, password);
        user.repository = new AuthRepository();

        try {
            view.showProgress(true);
            user.login(new BaseCallback<User>() {
                @Override
                public void onSuccessful(User u) {
                    App.setUser(u);
                    view.navigateToHome(u);
                    view.showProgress(false);
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.showProgress(false);
                    view.showError(error);
                }
            });
        } catch (Exception e) {
            view.showProgress(false);
            view.showError(e.getMessage());
        }
    }
}
