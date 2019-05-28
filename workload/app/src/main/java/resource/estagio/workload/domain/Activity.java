package resource.estagio.workload.domain;

import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;

public class Activity {

    private int id;
    private String name;
    private String demandNumber;
    private int activityType;
    private int customerId;
    private String customerName;
    private ActivityContract.IRepository repository;

    public Activity() {
    }

    public Activity(int id, String name, String demandNumber,
                    int activityType, int customerId, String customerName) {

        this.id = id;
        this.name = name;
        this.demandNumber = demandNumber;
        this.activityType = activityType;
        this.customerId = customerId;
        this.customerName = customerName;
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

    public String getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(String demandNumber) {
        this.demandNumber = demandNumber;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void deleteActivity(String token, BaseCallback<Void> onResult) throws Exception {
        if (repository == null) {
            throw new Exception("Repositório nulo");

        }
        if (id == 0) {
            throw new Exception("Id nulo");
        }
        
    }
}
