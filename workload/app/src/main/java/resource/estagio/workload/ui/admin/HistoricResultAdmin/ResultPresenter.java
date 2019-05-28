package resource.estagio.workload.ui.admin.HistoricResultAdmin;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
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
    private List<newListResultAdmin> newListResult= new ArrayList();
    private List<CustomerModel> listCustomer= new ArrayList<>();
    private List<ResultProject> listaGerson= new ArrayList<>();




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
                String aux="";
                String customerName = "";
                String projectName = "";
                int total = 0;

                for (CustomerModel model : listCustomer) {

                    for (TimeEntryModel timeEntryModel : value) {

                        if (timeEntryModel.getCustomerName().contains(model.getName())) {
                            customerName = timeEntryModel.getCustomerName();

//                            if(aux.equals(timeEntryModel.getActivityName())){
//                              total+= timeEntryModel.getHours();
//                                aux = timeEntryModel.getActivityName();
//                            }else {
//                                listaGerson.add(new ResultProject(aux,total));
//                                total=0;
//                                total += timeEntryModel.getHours();
//                                aux = timeEntryModel.getActivityName();
//
//                            }
                            if(aux.isEmpty() || aux.equals(timeEntryModel.getActivityName()) || aux == "" || aux==null) {
                                total+=timeEntryModel.getHours();

                                aux = timeEntryModel.getActivityName();
                            }else{
                                aux = timeEntryModel.getActivityName();
                                projectName += timeEntryModel.getActivityName() + "             " + total + "\n";
                                total=0;

                            }
                        }

                    }

                    if(!customerName.isEmpty()) {
                        newListResultAdmin listResultAdmin = new newListResultAdmin(customerName, projectName, 1, 1);
                        newListResult.add(listResultAdmin);
                    }
                    customerName="";
                }
                for (ResultProject resultProject : listaGerson) {
                    Log.e("TESTE",resultProject.getName()+" "+resultProject.getHoras());
                }

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
