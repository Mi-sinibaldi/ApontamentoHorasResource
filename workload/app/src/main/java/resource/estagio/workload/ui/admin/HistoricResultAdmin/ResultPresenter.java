package resource.estagio.workload.ui.admin.HistoricResultAdmin;

import java.util.ArrayList;
import java.util.Collections;
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
    private int hours = 0;
    private List<newListResultAdmin> newListResult = new ArrayList();
    private List<CustomerModel> listCustomer = new ArrayList<>();

    private String aux = "";
    private String auxCustomer = "";
    private int total = 0;


    public ResultPresenter(ResultHistoricContract.ResultView view) {
        this.view = view;
    }

    @Override
    public void getListResult(int month, int year) {

        getCustomers();
        hours = 0;
        view.dialog(true);
        List<ResultProject> listAuxProjects = new ArrayList<>();

        EmployeeDomain employeeDomain = new EmployeeDomain();
        employeeDomain.irepository = new EmployeeRepository();
        employeeDomain.getWorkList(month, year, new BaseCallback<List<TimeEntryModel>>() {
            @Override
            public void onSuccessful(List<TimeEntryModel> value) {

                Collections.sort(value);
                //listCustomer.clear();
                listAuxProjects.add(new ResultProject("0", "0", 0));
                for (CustomerModel model : listCustomer) {

                    for (TimeEntryModel timeEntryModel : value) {

                        if (timeEntryModel.getCustomerName().contains(model.getName())) {

                            if (aux.isEmpty() || aux.equals(timeEntryModel.getActivityName())) {
                                total += timeEntryModel.getHours();
                                aux = timeEntryModel.getActivityName();
                                auxCustomer = timeEntryModel.getCustomerName();
                                hours += timeEntryModel.getHours();
                            } else {
                                hours += timeEntryModel.getHours();
                                listAuxProjects.add(new ResultProject(auxCustomer, aux, total));
                                total = 0;
                                total += timeEntryModel.getHours();
                                aux = timeEntryModel.getActivityName();
                                auxCustomer = timeEntryModel.getCustomerName();
                            }

                            if (aux.isEmpty() || aux.equals(timeEntryModel.getActivityName())) {

                                aux = timeEntryModel.getActivityName();
                            } else {
                                aux = timeEntryModel.getActivityName();
                            }

                        }

                    }

                }


                listAuxProjects.add(new ResultProject(auxCustomer, aux, total));

                String auxNameCustomer = "";
                StringBuilder auxNameProject = new StringBuilder();
                for (ResultProject project : listAuxProjects) {

                    if (auxNameCustomer.equals("") || auxNameCustomer.equals(project.getClient())) {
                        auxNameProject.append(project.getName()).append("!").append(project.getHoras()).append("!");

                        auxNameCustomer = project.getClient();
                    } else {
                        newListResultAdmin listResultAdmin = new newListResultAdmin(auxNameCustomer,
                                auxNameProject.toString(), hours, 1);
                        newListResult.add(listResultAdmin);
                        auxNameProject = new StringBuilder();
                        auxNameProject.append(project.getName()).append("!").append(project.getHoras()).append("!");
                        auxNameCustomer = project.getClient();
                    }

                }
                newListResultAdmin listResultAdmin = new newListResultAdmin(auxNameCustomer,
                        auxNameProject.toString(), hours, 1);
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
                //view.showErrorMessage("Erro\n" + error);
                view.dialog(false);
            }
        });
    }


}
