package resource.estagio.workload.ui.admin.project.add_project;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityTypeModel;
import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.domain.Customer;
import resource.estagio.workload.domain.Project;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.ui.admin.HomeAdminContract;
import resource.estagio.workload.ui.admin.project.ProjectFragment;

public class AddProjectPresenter implements AddProjectContract.Presenter {
    private AddProjectContract.View view;
    private HomeAdminContract.View activityView;
    private ActivityTypeModel activityTypeModel;
    private Customer customer;

    public AddProjectPresenter(AddProjectContract.View view, HomeAdminContract.View activityView) {
        this.view = view;
        this.activityView = activityView;
    }

    @Override
    public void loadSpinner() {
        new ActivityRepository().getActivityType(App.getUser().getAccessToken(),
                new BaseCallback<List<ActivityTypeModel>>() {
                    @Override
                    public void onSuccessful(List<ActivityTypeModel> value) {
                        view.spinnerList(value);

                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showToast(error);
                    }
                });
    }

    @Override
    public void getItemInSpinner(AdapterView<?> parent, int position) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ActivityTypeModel activityTypeModel = (ActivityTypeModel) parent.getItemAtPosition(position);
        this.activityTypeModel = activityTypeModel;
    }

    @Override
    public void addProject(String nameProject, String demandNumber, Customer customer) {
        this.customer = customer;
        Project project = new Project(0, nameProject, demandNumber, activityTypeModel, customer);
        try {
            project.insertProject(new BaseCallback<String>() {
                @Override
                public void onSuccessful(String value) {
                    showDialogConfirm(value);
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
    }

    @Override
    public void updateProject(int id, String nameProject, String demandNumber, Customer customer) {

        this.customer = customer;
        Project project = new Project(id, nameProject, demandNumber, activityTypeModel, customer);
        try{
            project.updateProject(new BaseCallback<String>() {
                @Override
                public void onSuccessful(String value) {
                    showDialogConfirm(value);
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.showToast(error);
                }
            });
        }catch (Exception e){
            view.showToast(e.getMessage());
            e.printStackTrace();
        }

    }

    private void showDialogConfirm(String value) {
        Dialog dialog = new Dialog(view.getActivity(), R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_check);
        dialog.setCancelable(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();

        TextView text = dialog.findViewById(R.id.textDialog);
        text.setText(value);
        Button buttonConfirmCheck = dialog.findViewById(R.id.button_dialog_error);
        buttonConfirmCheck.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putSerializable("customer", new CustomerModel(customer.getId(), customer.getName()));
            ProjectFragment fragment = new ProjectFragment(activityView);
            fragment.setArguments(bundle);
            view.getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.frame_admin, fragment
            ).commit();
            dialog.dismiss();

        });


    }


}
