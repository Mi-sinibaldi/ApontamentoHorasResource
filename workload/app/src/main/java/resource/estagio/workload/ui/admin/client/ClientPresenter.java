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

public class ClientPresenter implements ClientContract.Presenter {

    private ClientFragment view;

    public ClientPresenter(ClientFragment view) {
        this.view = view;
    }

    @Override
    public void getCustomers(boolean status) {
        view.showProgressClient(true);
        new CustomerRepository().getCustomers(App.getUser().getAccessToken(),
                new BaseCallback<List<CustomerModel>>() {
                    @Override
                    public void onSuccessful(List<CustomerModel> value) {
                        view.setRecyclerClient(value, status);
                        view.showProgressClient(false);
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showToast(error);
                        view.showProgressClient(false);
                    }
                });
    }

    @Override
    public void deleteCustomer(CustomerModel model) {
        view.showProgressClient(true);
        try {
            Customer customer = new Customer(model.getId(), model.getName());
            customer.repository = new CustomerRepository();
            customer.deleteCustomer(App.getUser().getAccessToken(), new BaseCallback<Void>() {
                @Override
                public void onSuccessful(Void value) {
                    view.showProgressClient(false);
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.showProgressClient(false);
                    view.showToast(error);
                }
            });
            getCustomers(true);
        } catch (Exception e) {
            view.showToast(e.getMessage());
        }
    }

    @Override
    public void showDialogCustomer() {
        loadComponentsDialog(createDialog());
    }

    private Dialog createDialog() {
        Dialog dialog = new Dialog(view.getActivity(), R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_new_customer);
        dialog.setCancelable(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
        return dialog;
    }

    private void loadComponentsDialog(Dialog dialog) {
        TextInputLayout name = dialog.findViewById(R.id.text_add_customer);
        Button save = dialog.findViewById(R.id.button_save_customer);
        Button cancel = dialog.findViewById(R.id.button_cancel_customer);

        clickButtonSave(dialog, name, save);

        cancel.setOnClickListener(v -> dialog.dismiss());
    }

    private void clickButtonSave(Dialog dialog, TextInputLayout name, Button save) {
        save.setOnClickListener(v -> {
            Customer customer = new Customer(0, name.getEditText().getText().toString().trim());
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
    }
}
