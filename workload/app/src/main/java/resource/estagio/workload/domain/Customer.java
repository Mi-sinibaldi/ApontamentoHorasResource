package resource.estagio.workload.domain;

import android.content.Context;

import java.io.Serializable;

import resource.estagio.workload.infra.BaseCallback;

public class Customer {

    public CustomerContract.IRepository repository;
    private Context context;

    private int id;
    private String name;

    public Customer(Context context, int id, String name) {
        this.context = context;
        this.id = id;
        this.name = name;
    }

    public Customer(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void postCustomer(String token, BaseCallback<String> onResult) throws Exception {
        if (name.isEmpty() || name == null) {
            throw new Exception("Name can't be empty or null");
        }
        if (id < 0) {
            throw new Exception("id can't be null ");
        }
        if (repository == null) {
            throw new Exception("Repository can't be empty");
        }
        repository.postCustomer(this, token, new BaseCallback<String>() {
            @Override
            public void onSuccessful(String value) {
                onResult.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                onResult.onUnsuccessful(error);
            }
        });
    }


    public void deleteCustomer(String token, BaseCallback<Void> onResult) throws Exception {
        if (repository == null) {
            throw new Exception("Repositorio vazio");
        }
        if (id == 0) {
            throw new Exception("Campo Id nao pode ser vazio");
        }
        repository.deleteCustomer(id, token, new BaseCallback<Void>() {
            @Override
            public void onSuccessful(Void value) {
                onResult.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                onResult.onUnsuccessful(error);
            }
        });
    }
}

