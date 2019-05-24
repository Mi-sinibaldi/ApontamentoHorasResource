package resource.estagio.workload.domain;

import android.content.Context;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.infra.BaseCallback;

public class Customer {

    public  CustomerContract.IRepository repository;
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

    public void getCustomers(String token, BaseCallback<List<CustomerModel>> onResult) throws Exception {
        if(repository == null)
            throw new Exception("Repositório Vazio");
        repository.getCustomers(token, new BaseCallback<List<CustomerModel>>() {
            @Override
            public void onSuccessful(List<CustomerModel> value) {
                onResult.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                onResult.onUnsuccessful(error);
            }
        });
    }
}
