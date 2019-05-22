package resource.estagio.workload.domain.employee;

import android.content.Context;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.infra.BaseCallback;

public class EmployeeDomain {

    public EmployeeContract.IRepository irepository;
    private Context context;

    private String name;
    private String username;
    private int activityId;
    private String activityName;
    private int customerId;
    private String customerName;
    private String demandNumber;
    private int hours;
    private String date;
    private String reason;

    public EmployeeDomain(Context context, int activityId, String activityName, int customerId,
                 String customerName, String demandNumber, int hours, String date, String reason) {

        this.context = context;
        this.activityId = activityId;
        this.activityName = activityName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.demandNumber = demandNumber;
        this.hours = hours;
        this.date = date;
        this.reason = reason;
    }

    public EmployeeDomain() {

    }

    public EmployeeContract.IRepository getIrepository() {
        return irepository;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public String getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(String demandNumber) {
        this.demandNumber = demandNumber;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void getPostEntry(){

    }

    public void postEntry(String token, final BaseCallback<Void> listener) throws Exception {
        if(getIrepository() == null)
            throw new Exception("Repositório Vazio");
        if(activityId == 0 || activityName.isEmpty() || demandNumber.isEmpty())
            throw new Exception("Cliente sem Projeto");
        if(customerId == 0 || customerName.isEmpty())
            throw new Exception("Cliente não encontrado");
        if(hours == 0)
            throw new Exception("É necessário apontamento de no minimo uma hora");
        if(date == null)
            throw  new Exception("Data inválida");
        if(reason.isEmpty())
            throw new Exception("É necessário ter descrição da atividade");

        TimeEntryModel model = new TimeEntryModel(activityId, activityName, customerId,
                customerName, demandNumber, hours, date, reason);
        irepository.postEntry(model, token, new BaseCallback<Void>() {
            @Override
            public void onSuccessful(Void value) {
                listener.onSuccessful(null);
            }

            @Override
            public void onUnsuccessful(String error) {
                listener.onUnsuccessful(error);
            }
        });

    }

    public void getWorkList(int month, int year, final BaseCallback<List<TimeEntryModel>> listener) {
        irepository.getWorkList(month, year, new BaseCallback<List<TimeEntryModel>>() {
            @Override
            public void onSuccessful(List<TimeEntryModel> value) {
                listener.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                listener.onUnsuccessful(error);
            }
        });
    }

}