package resource.estagio.workload.ui.login;

class LoginContract {

    interface View {
        void showProgress(final boolean show);

        void showError(String error);

        void navigateToHome();
    }

    interface Presenter {
        void login(String username, String password);
    }
}
