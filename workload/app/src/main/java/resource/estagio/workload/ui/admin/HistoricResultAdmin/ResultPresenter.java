package resource.estagio.workload.ui.admin.HistoricResultAdmin;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.data.repository.CustomerRepository;
import resource.estagio.workload.data.repository.EmployeeRepository;
import resource.estagio.workload.domain.employee.EmployeeDomain;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;

public class ResultPresenter implements ResultHistoricContract.ResultPresenter {

    private ResultHistoricContract.ResultView view;
    private long hours = 0;
    private List<newListResultAdmin> newListResult = new ArrayList();
    private List<CustomerModel> listCustomer = new ArrayList<>();
    private List<ResultProject> listAuxProjects = new ArrayList<>();

    String aux = "";
    String auxCustomer = "";
    String customerName = "";
    String projectName = "";
    int total = 0;


    public ResultPresenter(ResultHistoricContract.ResultView view) {
        this.view = view;
    }

    @Override
    public void getListResult(int month, int year) {
        getCustomers();
        hours = 0;
        view.dialog(true);


        EmployeeDomain employeeDomain = new EmployeeDomain();
        employeeDomain.irepository = new EmployeeRepository();

        employeeDomain.getWorkList(month, year, new BaseCallback<List<TimeEntryModel>>() {
            @Override
            public void onSuccessful(List<TimeEntryModel> value) {


                for (CustomerModel model : listCustomer) {


                    for (TimeEntryModel timeEntryModel : value) {

                        if (timeEntryModel.getCustomerName().contains(model.getName())) {
                            customerName = timeEntryModel.getCustomerName();

                            if (aux.isEmpty() || aux.equals(timeEntryModel.getActivityName())) {
                                total += timeEntryModel.getHours();
                                aux = timeEntryModel.getActivityName();
                                auxCustomer = timeEntryModel.getCustomerName();
                            } else {
                                listAuxProjects.add(new ResultProject(auxCustomer,aux, total));
                                total = 0;
                                total += timeEntryModel.getHours();
                                aux = timeEntryModel.getActivityName();
                                auxCustomer = timeEntryModel.getCustomerName();
                            }

                            if (aux.isEmpty() || aux.equals(timeEntryModel.getActivityName())) {

                                projectName += timeEntryModel.getActivityName() + "             " + timeEntryModel.getHours() + "\n";
                                aux = timeEntryModel.getActivityName();
                            } else {
                                projectName += timeEntryModel.getActivityName() + "             " + timeEntryModel.getHours() + "\n";
                                aux = timeEntryModel.getActivityName();
                            }

                        }

                    }


                    projectName = "";
                }



                listAuxProjects.add(new ResultProject(auxCustomer, aux, total));

                String auxNameCustomer = "", auxNameProject = "";
                for (ResultProject project : listAuxProjects) {

                    if (auxNameCustomer.equals("") || auxNameCustomer.equals(project.getClient())) {
                        auxNameProject += project.getName() + "!" + project.getHoras() + "!";

                        auxNameCustomer = project.getClient();
                    } else {
                        newListResultAdmin listResultAdmin = new newListResultAdmin(auxNameCustomer, auxNameProject, 1, 1);
                        newListResult.add(listResultAdmin);
                        auxNameProject = "";
                        auxNameProject += project.getName() + "!" + project.getHoras() + "!";
                        auxNameCustomer = project.getClient();
                    }

                }
                newListResultAdmin listResultAdmin = new newListResultAdmin(auxNameCustomer, auxNameProject, 1, 1);
                newListResult.add(listResultAdmin);


                view.showListResult(newListResult);

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


    private void getCustomers() {
        CustomerRepository repository = new CustomerRepository();
        repository.getCustomers(App.getUser().getAccessToken(), new BaseCallback<List<CustomerModel>>() {
            @Override
            public void onSuccessful(List<CustomerModel> value) {
                listCustomer = value;
            }

            @Override
            public void onUnsuccessful(String error) {

            }
        });
    }


}
