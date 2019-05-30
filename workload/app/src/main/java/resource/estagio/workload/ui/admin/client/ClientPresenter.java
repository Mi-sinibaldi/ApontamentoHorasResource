package resource.estagio.workload.ui.admin.client;

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import resource.estagio.workload.R;
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
    public void deleteCustomer(CustomerModel model) {
        view.showProgressClient(true);
        try{
            Customer customer = new Customer(model.getId(),model.getName());
            customer.repository = new CustomerRepository();
            customer.deleteCustomer(App.getUser().getAccessToken(), new BaseCallback<Void>() {
                @Override
                public void onSuccessful(Void value) { view.showProgressClient(false);}

                @Override
                public void onUnsuccessful(String error) {
                    view.showProgressClient(false);
                    view.notification(error);
                }
            });

            getCustomers(true);
        }catch (Exception e){
            view.notification(e.getMessage());
        }

    }

    @Override
    public void showDialogCustomer() {
        Dialog dialog = new Dialog(view.getActivity(), R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_new_customer);
        dialog.setCancelable(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();

        TextInputLayout name = dialog.findViewById(R.id.text_add_customer);
        Button save = dialog.findViewById(R.id.button_save_customer);
        Button cancel = dialog.findViewById(R.id.button_cancel_customer);
        save.setOnClickListener(v -> {
            Customer customer = new Customer(0, name.getEditText().getText().toString());
            customer.repository = new CustomerRepository();
            try {
                customer.postCustomer(App.getUser().getAccessToken(), new BaseCallback<String>() {
                    @Override
                    public void onSuccessful(String value) {
                        view.showToast(value);
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showToast(error);

                    }
                });
            } catch (Exception e) {
                view.showToast(e.getMessage());
                e.printStackTrace();
            }

            dialog.dismiss();

        });

        cancel.setOnClickListener(v -> dialog.dismiss());
    }
}
