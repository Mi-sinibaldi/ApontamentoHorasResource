package resource.estagio.workload.domain;

import android.content.Context;

import resource.estagio.workload.R;
import resource.estagio.workload.infra.BaseCallback;

public class User {

    public UserContract.IRepository repository;

    private String username;
    private String password;

    private String accessToken;
    private long id;
    private String name;
    private boolean isAdmin;

    public Context context;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String accessToken, long id, String name, boolean isAdmin) {
        this.accessToken = accessToken;
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void login(final BaseCallback<User> listener) throws Exception {

        if (repository == null)
            throw new Exception(context.getString(R.string.required_repository_is_null));

        if (username == null || username.isEmpty())
            throw new Exception(context.getString(R.string.username_is_null_or_empty));

        if (password == null || password.isEmpty())
            throw new Exception(context.getString(R.string.required_password_is_null_or_empty));

        if (password.length() < 4)
            throw new Exception(context.getString(R.string.password_must_have_at_least_four_characters));

        repository.login(username, password, new BaseCallback<User>() {
            @Override
            public void onSuccessful(User user) {
                //ARMAZENAR O TOKEN
                listener.onSuccessful(user);
            }

            @Override
            public void onUnsuccessful(String error) {
                listener.onUnsuccessful(error);
            }
        });
    }
}
