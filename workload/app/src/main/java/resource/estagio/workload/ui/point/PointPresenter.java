package resource.estagio.workload.ui.point;

import java.util.Calendar;
import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.repository.CustomerRepository;
import resource.estagio.workload.infra.BaseCallback;

public class PointPresenter implements PointContract.Presenter{

    private PointContract.View view;


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
    public void getActivities(long id) {

    }
}
