package resource.estagio.workload.domain.employee;

import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.infra.BaseCallback;

public class EmployeeDomain {
    public EmployeeContract.IRepository irepository;

    private long id;
    private String name;
    private String username;
    private boolean isAdmin;

    public EmployeeContract.IRepository getIrepository() {
        return irepository;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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