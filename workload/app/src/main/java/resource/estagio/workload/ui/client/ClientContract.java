package resource.estagio.workload.ui.client;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;

public class ClientContract {
    interface View {
        void setRecyclerClient(List<CustomerModel> customerModels);
        void notification(String messenge);
    }

    interface Presenter {
        void getCustomers();
        void deleteCustomer(int id, String name, int position);

    }
}
