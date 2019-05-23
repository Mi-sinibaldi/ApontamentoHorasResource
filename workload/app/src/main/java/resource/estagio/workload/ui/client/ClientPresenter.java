package resource.estagio.workload.ui.client;

import android.widget.Toast;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.repository.CustomerRepository;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;

public class ClientPresenter implements ClientContract.Presenter{

    private ClientFragment view;

    public ClientPresenter(ClientFragment view) {
        this.view=view;
    }

    @Override
    public void getCustomers() {
        CustomerRepository repository = new CustomerRepository();
        repository.getCustomers(App.getUser().getAccessToken(),
                new BaseCallback<List<CustomerModel>>() {
            @Override
            public void onSuccessful(List<CustomerModel> value) {
                view.setRecyclerClient(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                view.notification(error);
            }
        });
    }
}
