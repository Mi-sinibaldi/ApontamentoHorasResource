package resource.estagio.workload.ui.point;

import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.data.repository.CustomerRepository;
import resource.estagio.workload.data.repository.EmployeeRepository;
import resource.estagio.workload.domain.employee.EmployeeDomain;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;

public class PointPresenter implements PointContract.Presenter{

    private PointContract.View view;

    public PointPresenter(PointContract.View view) {
        this.view = view;
    }

    @Override
    public void setPoint(String date, String hour, String customerName, int customerId,
                         String projectName, int projectId, String demandNumber, String reason) {
        if(validateFilds(reason, hour)) return;

        view.showProgressAdd(true);

        try{
            EmployeeDomain employee = new EmployeeDomain(view.getContext(), projectId, projectName,
                customerId, customerName, demandNumber, Integer.parseInt(hour), date, reason);
            employee.irepository = new EmployeeRepository();

            employee.postEntry(App.getUser().getAccessToken(), new BaseCallback<Void>() {
                @Override
                public void onSuccessful(Void value) {
                    view.showDialog();
                    view.showProgressAdd(false);
                    view.setClearFields();
                    view.enabledNavigation(false);
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.showProgressAdd(false);
                    view.enabledNavigation(false);
                    view.notification(error);
                }
            });
        }catch (NumberFormatException e){
            view.notification("Numero de horas esta vazia");
            view.showProgressAdd(false);
            view.enabledNavigation(false);
        } catch (Exception e) {
            view.showProgressAdd(false);
            view.enabledNavigation(false);
            view.notification(e.getMessage());
        }
    }

    private boolean validateFilds(String reason, String hour) {
        boolean error = false;
        if(reason.isEmpty()){ view.setErrorReasonField("Coloque um motivo"); error = true; }
        try {
            if(Integer.parseInt(hour) == 0){ view.setErrorHourField("Coloque suas horas"); error = true; }
        }catch (NumberFormatException e){
            view.setErrorHourField("Coloque suas horas"); error = true;
        }
        return error;
    }

    @Override
    public void getCustumers() {
        view.showProgressCustomer(true);
        view.enabledNavigation(true);
        CustomerRepository repository = new CustomerRepository();
    repository.getCustomers(App.getUser().getAccessToken(), new BaseCallback<List<CustomerModel>>() {
        @Override
        public void onSuccessful(List<CustomerModel> value) {
            view.loadSpinnerCustomer(value);
            view.showProgressCustomer(false);
            view.enabledNavigation(false);
        }

        @Override
        public void onUnsuccessful(String error) {
            view.notification(error);
            view.enabledNavigation(false);
        }
    });


    }

    @Override
    public void getActivities(int id) {
        view.showProgressProject(true);
        ActivityRepository repository = new ActivityRepository();
        repository.getActivity(id, App.getUser().getAccessToken(), new BaseCallback<List<ActivityModel>>() {
            @Override
            public void onSuccessful(List<ActivityModel> value) {
                view.showProgressProject(false);
                view.enabledNavigation(false);
                if(value.isEmpty()){
                    view.disableSpinnerActivity();
                    view.notification("Lista vazia");
                }else
                    view.loadSpinnerActivity(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                view.showProgressProject(false);
                view.enabledNavigation(false);
            }
        });
    }
}
