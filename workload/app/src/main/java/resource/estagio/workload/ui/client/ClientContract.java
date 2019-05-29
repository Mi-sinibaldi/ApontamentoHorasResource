package resource.estagio.workload.ui.client;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;

public class ClientContract {

    interface View {

        void setRecyclerClient(List<CustomerModel> customerModels, boolean status);

        void notification(String messenge);

        void showAdapterRecycler(boolean status);

        void showProgressClient(final boolean show);

        void showToast(String value);
    }

    interface Presenter {

        void getCustomers(boolean status);

        void deleteCustomer(CustomerModel model);

        void showDialogCustomer();
    }
}
