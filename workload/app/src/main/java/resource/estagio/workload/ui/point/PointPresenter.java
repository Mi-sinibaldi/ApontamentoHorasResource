package resource.estagio.workload.ui.point;

import java.util.Calendar;
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
        validateHour(hour);
        validateReason(reason);

        try{
            EmployeeDomain employee = new EmployeeDomain(view.getContext(), projectId, projectName,
                customerId, customerName, demandNumber, Integer.parseInt(hour), date, reason);
            employee.irepository = new EmployeeRepository();

            employee.postEntry(App.getUser().getAccessToken(), new BaseCallback<Void>() {
                @Override
                public void onSuccessful(Void value) {
                    view.showDialog();
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.notification(error);
                }
            });
        }catch (NumberFormatException e){
            view.notification("Numero de horas esta vazia");
        } catch (Exception e) {
            view.notification(e.getMessage());
        }
    }

    private void validateReason(String reason) {
        if(reason.isEmpty())
            view.setRedReason(true);
        else
            view.setRedReason(false);
    }

    private void validateHour(String hour) {
        try{
            if(Integer.parseInt(hour) == 0)
                view.setRedHour(true);
            else
                view.setRedHour(false);
        }catch (NumberFormatException e){
            view.setRedHour(true);
        }
    }

    @Override
    public void getCustumers() {
        CustomerRepository repository = new CustomerRepository();
    repository.getCustomers(App.getUser().getAccessToken(), new BaseCallback<List<CustomerModel>>() {
        @Override
        public void onSuccessful(List<CustomerModel> value) {
            view.loadSpinnerCustomer(value);
        }

        @Override
        public void onUnsuccessful(String error) {
            view.notification(error);
        }
    });

    }

    @Override
    public void getActivities(int id) {
        ActivityRepository repository = new ActivityRepository();
        repository.getActivity(id, App.getUser().getAccessToken(), new BaseCallback<List<ActivityModel>>() {
            @Override
            public void onSuccessful(List<ActivityModel> value) {
                if(value.isEmpty()){
                    view.disableSpinnerActivity();
                    view.notification("Lista vazia");
                }else
                    view.loadSpinnerActivity(value);
            }

            @Override
            public void onUnsuccessful(String error) {

            }
        });
    }
}
