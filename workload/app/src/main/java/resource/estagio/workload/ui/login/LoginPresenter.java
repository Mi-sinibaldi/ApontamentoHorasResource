package resource.estagio.workload.ui.login;

import resource.estagio.workload.data.repository.AuthRepository;
import resource.estagio.workload.domain.User;
import resource.estagio.workload.infra.BaseCallback;

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
            user.login(new BaseCallback<Void>() {
                @Override
                public void onSuccessful(Void v) {
                    view.showProgress(false);
                    view.navigateToHome();
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
