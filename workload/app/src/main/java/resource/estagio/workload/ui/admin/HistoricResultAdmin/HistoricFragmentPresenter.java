package resource.estagio.workload.ui.admin.HistoricResultAdmin;

import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.data.repository.EmployeeRepository;
import resource.estagio.workload.domain.employee.EmployeeDomain;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.ui.timeline.TimeLineContract;

public class HistoricFragmentPresenter implements ResultHistoricContract.historicPresenter {

    private ResultHistoricContract.historicView view;
    private long hours = 0;

    public HistoricFragmentPresenter(ResultHistoricContract.historicView view) {
        this.view = view;
    }

    @Override
    public void getTimeline(int month, int year) {
        hours = 0;
        view.dialog(true);
        EmployeeDomain employeeDomain = new EmployeeDomain();
        employeeDomain.irepository = new EmployeeRepository();

        employeeDomain.getWorkList(month, year, new BaseCallback<List<TimeEntryModel>>() {
            @Override
            public void onSuccessful(List<TimeEntryModel> value) {
                view.showListTimeline(value);
                view.showSucessMessage("Sucesso");
                view.dialog(false);
            }

            @Override
            public void onUnsuccessful(String error) {
                view.showErrorMessage("Erro\n" + error);
                view.dialog(false);
            }
        });
    }
}
