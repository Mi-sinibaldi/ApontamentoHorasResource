package resource.estagio.workload.domain;

import android.content.Context;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityTypeModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;

public class Project {

    private int id;
    private String nameProject;
    private String demandNumber;
    private ActivityTypeModel activityTypeModel;
    private Customer customer;
    public ProjectContract.IRepository repository;

    private Context context;

    public Project(int id, String nameProject, String demandNumber,
                   ActivityTypeModel activityTypeModel, Customer customer) {

        this.id = id;
        this.nameProject = nameProject;
        this.demandNumber = demandNumber;
        this.activityTypeModel = activityTypeModel;
        this.customer = customer;
        this.repository = new ActivityRepository();
    }

    public Project(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getDemandNumber() {
        return demandNumber;
    }

    public ActivityTypeModel getActivityTypeModel() {
        return activityTypeModel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void insertProject(BaseCallback<String> onResult) throws Exception {

        if (nameProject.isEmpty() || nameProject == null) {
            throw new Exception(context.getString(R.string.required_name_is_null_or_empty));
        }
        if (demandNumber.isEmpty() || demandNumber == null) {
            throw new Exception(context.getString(R.string.required_demand_number_is_null_or_empty));
        }
        if (activityTypeModel == null) {
            throw new Exception(context.getString(R.string.required_activity_type_is_null));
        }
        if (customer == null) {
            throw new Exception(context.getString(R.string.required_customer_is_null));
        }
        if (repository == null) {
            throw new Exception(context.getString(R.string.required_repository_is_null));
        }

        repository.insertProject(this, App.getUser().getAccessToken(), new BaseCallback<String>() {
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

    public void deleteActivity(String token, BaseCallback<String> onResult) throws Exception {

        if (repository == null) {
            throw new Exception(context.getString(R.string.null_repository));
        }
        if (id == 0) {
            throw new Exception(context.getString(R.string.null_id));
        }

        repository.deleteProject(id, token, new BaseCallback<String>() {
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

}
