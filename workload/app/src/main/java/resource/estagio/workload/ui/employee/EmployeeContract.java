package resource.estagio.workload.ui.employee;

import resource.estagio.workload.ui.employee.adapterEmployee.AdapterEmployee;

public class EmployeeContract {

    public interface View {

        void adapterResult(AdapterEmployee adapterEmployee);
    }

    public interface Presenter {
        void filter(String nome);

        void createCollaborator();

    }
}
