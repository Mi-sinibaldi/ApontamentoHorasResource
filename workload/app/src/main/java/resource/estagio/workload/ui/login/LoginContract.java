package resource.estagio.workload.ui.login;

import resource.estagio.workload.domain.User;

class LoginContract {

    interface View {
        void showProgress(final boolean show);

        void showError(String error);

        void navigateToHome(User user);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
