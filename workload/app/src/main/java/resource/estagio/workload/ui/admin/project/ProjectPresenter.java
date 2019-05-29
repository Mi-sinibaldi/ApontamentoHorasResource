package resource.estagio.workload.ui.admin.project;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.domain.Project;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.ui.admin.project.adapterProject.AdapterProject;

public class ProjectPresenter implements ProjectContract.Presenter {

    private ProjectContract.View view;
    private AdapterProject adapter;

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
                            view.showToast(R.string.no_records_found);
                            view.showProgress(false);
                            return;
                        }
                        view.listAdapter(value, status);
                        view.showProgress(false);
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showError(error);
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
                    view.showToast(R.string.project_deleted_successfully);
                    view.reloadList();
                }

                @Override
                public void onUnsuccessful(String error) {
                    view.showProgress(false);
                    view.showError(R.string.project_not_deleted);
                    view.reloadList();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            view.showProgress(false);
            view.showError(e.getMessage());
        }

    }

}
