package resource.estagio.workload.ui.admin.employee;


import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.data.remote.model.EmployeeModel;
import resource.estagio.workload.data.repository.EmployeeRepository;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.ConstantApp;
import resource.estagio.workload.ui.DialogApp;
import resource.estagio.workload.ui.admin.employee.adapterEmployee.AdapterEmployee;

public class EmployeePresenter implements EmployeeContract.Presenter {

    private EmployeeContract.View view;
    private List<EmployeeModel> employee = new ArrayList<>();
    private AdapterEmployee adapterEmployee;

    public EmployeePresenter(EmployeeContract.View view) {
        this.view = view;
    }


    @Override
    public void filter(String nome) {
        ArrayList<EmployeeModel> filterList = new ArrayList<>();
        if(adapterEmployee != null) {
            for (EmployeeModel colaborador : employee) {
                if (colaborador.getName().toLowerCase().contains(nome.toLowerCase())) {
                    filterList.add(colaborador);
                } else if (colaborador.getRe().toLowerCase().contains(nome.toLowerCase())) {
                    filterList.add(colaborador);
                }
            }
            if (filterList == null) {
                adapterEmployee.filterAdapter(employee);
                view.adapterResult(adapterEmployee);
            } else {
                adapterEmployee.filterAdapter(filterList);
                view.adapterResult(adapterEmployee);
            }
        }
    }

    @Override
    public void listEmployee() {
        view.showProgress(true);
        new EmployeeRepository().listEmployee(App.getUser().getAccessToken(),
                new BaseCallback<List<EmployeeModel>>() {
                    @Override
                    public void onSuccessful(List<EmployeeModel> value) {
                        employee = value;
                        adapterEmployee = new AdapterEmployee(employee);

                        view.adapterResult(adapterEmployee);
                        view.showProgress(false);
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        view.showProgress(false);
                        if(errorConnection(error)) return;
                        view.showDialog(error,false);
                    }
                });

    }

    private boolean errorConnection(String error) {
        if (error.equals(ConstantApp.CONNECTION_INTERNET)) {
            DialogApp.showDialogConnection(view.getActivity());
            return true;
        }
        return false;
    }

}
