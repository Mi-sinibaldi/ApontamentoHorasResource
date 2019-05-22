package resource.estagio.workload.domain;

import android.content.Context;

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
}
