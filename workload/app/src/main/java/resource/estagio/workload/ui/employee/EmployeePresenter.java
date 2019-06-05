package resource.estagio.workload.ui.employee;


import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.data.remote.model.EmployeeModel;
import resource.estagio.workload.ui.employee.adapterEmployee.AdapterEmployee;

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

        for (EmployeeModel colaborador : employee) {
            if (colaborador.getNome().toLowerCase().contains(nome.toLowerCase())) {
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

    @Override
    public void createCollaborator() {
        EmployeeModel employeeModel = new EmployeeModel("Michelle Sinibaldi", "re037933");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Elivel Nascimento", "re037926");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Gabriella Aleo", "re037929");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Guilherme Matos", "re037954");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Guilherme Sassa", "re024242");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Gerson Junior", "re0379534");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Catia Souza", "re0379545");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Mateus Matos", "re0379523");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Marcus Galdino", "re0379501");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Igor Oliveira", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Andrey Little", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Gabriel Couto", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Lucas Balloon", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Paulo Henrique", "re032398");
        employee.add(employeeModel);

        adapterEmployee = new AdapterEmployee(employee);

        view.adapterResult(adapterEmployee);
    }

}
