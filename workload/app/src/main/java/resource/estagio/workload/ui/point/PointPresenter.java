package resource.estagio.workload.ui.point;

import java.util.Calendar;
import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.data.repository.CustomerRepository;
import resource.estagio.workload.infra.BaseCallback;

public class PointPresenter implements PointContract.Presenter{

    private PointContract.View view;

    public PointPresenter(PointContract.View view) {
        this.view = view;
    }

    @Override
    public void setPoint(Calendar date, Calendar time, String customer, int customerId,
                         String project, int projectId, String demandNumber, String reason) {

    }

    @Override
    public void getCustumers() {
        CustomerRepository repository = new CustomerRepository();
    repository.getCustomers(" ", new BaseCallback<List<CustomerModel>>() {
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
        repository.getActivity(id, "", new BaseCallback<List<ActivityModel>>() {
            @Override
            public void onSuccessful(List<ActivityModel> value) {
                view.loadSpinnerActivity(value);
            }

            @Override
            public void onUnsuccessful(String error) {

            }
        });
    }
}
