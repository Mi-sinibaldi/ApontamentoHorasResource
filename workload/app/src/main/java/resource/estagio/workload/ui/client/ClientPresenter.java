package resource.estagio.workload.ui.client;

import android.widget.ImageView;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.repository.CustomerRepository;
import resource.estagio.workload.domain.Customer;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;

public class ClientPresenter implements ClientContract.Presenter{

    private ClientFragment view;

    public ClientPresenter(ClientFragment view) { this.view = view; }

    @Override
    public void getCustomers(boolean status) {
        view.showProgressClient(true);
        CustomerRepository repository = new CustomerRepository();
        repository.getCustomers(App.getUser().getAccessToken(),
                new BaseCallback<List<CustomerModel>>() {
            @Override
            public void onSuccessful(List<CustomerModel> value) {
                view.setRecyclerClient(value, status);
                view.showProgressClient(false);

            }

            @Override
            public void onUnsuccessful(String error) {
                view.notification(error);
                view.showProgressClient(false);
            }
        });
    }

    @Override
    public void deleteCustomer(List<CustomerModel> models) {
        try{
            for (CustomerModel model: models){
                Customer customer = new Customer(model.getId(),model.getName());
                customer.repository = new CustomerRepository();
                customer.deleteCustomer(App.getUser().getAccessToken(), new BaseCallback<Void>() {
                    @Override
                    public void onSuccessful(Void value) { }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.notification(error);
                    }
                });
            }
            getCustomers(true);
        }catch (Exception e){
            view.notification(e.getMessage());
        }

    }
}
