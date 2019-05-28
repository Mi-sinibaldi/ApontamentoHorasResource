package resource.estagio.workload.domain;

import resource.estagio.workload.infra.BaseCallback;

public class  User {

    public UserContract.IRepository repository;

    private String username;
    private String password;

    private String accessToken;
    private long id;
    private String name;
    private boolean isAdmin;

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

    public void login(final BaseCallback<User> listener)
            throws Exception {

        if (repository == null)
            throw new Exception("Required repository is null");

        if (username == null || username.isEmpty())
            throw new Exception("Required username is null or empty");

        if (password == null || password.isEmpty())
            throw new Exception("Required password is null or empty");

        if (password.length() < 4)
            throw new Exception("Password must have at least 4 characters");

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
