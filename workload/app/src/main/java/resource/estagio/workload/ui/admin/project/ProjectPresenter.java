package resource.estagio.workload.ui.admin.project;

import java.util.List;

import resource.estagio.workload.infra.ConstantApp;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.domain.Project;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;

public class ProjectPresenter implements ProjectContract.Presenter {

    private ProjectContract.View view;


    public ProjectPresenter(ProjectContract.View view) {
        this.view = view;
    }

    @Override
    public void loadList(int idCustomer, boolean status) {

        view.showProgress(true);

        new ActivityRepository().getActivity(idCustomer, App.getUser().getAccessToken(),
                new BaseCallback<List<ActivityModel>>() {
                    @Override
                    public void onSuccessful(List<ActivityModel> value) {
                        if (value == null) {
                            view.showToast(ConstantApp.REGISTER_NOT_FOUND);
                            view.showProgress(false);
                            return;
                        }
                        view.listAdapter(value, status);
                        view.showProgress(false);
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showToast(error);
                        view.showProgress(false);
                    }
                });
    }

    @Override
    public void deleteCustomer(ActivityModel model) {

        view.showProgress(true);
        Project project = new Project(model.getId());
        project.repository = new ActivityRepository();

        try {
            project.deleteActivity(App.getUser().getAccessToken(), new BaseCallback<String>() {
                @Override
                public void onSuccessful(String value) {
                    view.showProgress(false);
                    view.showToast(value);
                    view.reloadList();
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.showProgress(false);
                    view.showToast(error);
                    view.reloadList();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            view.showProgress(false);
            view.showToast(e.getMessage());
        }
    }

}
