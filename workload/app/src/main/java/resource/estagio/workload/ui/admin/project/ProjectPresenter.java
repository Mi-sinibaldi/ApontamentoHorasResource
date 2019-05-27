package resource.estagio.workload.ui.admin.project;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.ui.admin.project.adapterProject.AdapterProject;

public class ProjectPresenter implements ProjectContract.Presenter {

    private ProjectContract.View view;

    public ProjectPresenter(ProjectContract.View view) {
        this.view = view;
    }

    @Override
    public void loadList(int idCustomer) {
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
                        view.listAdapter(new AdapterProject(value));
                        view.showProgress(false);
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showError(error);
                        view.showProgress(false);
                    }
                });
    }
}
