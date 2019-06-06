package resource.estagio.workload.ui.admin.employee;

import resource.estagio.workload.ui.admin.employee.adapterEmployee.AdapterEmployee;

public class EmployeeContract {

    public interface View {

        void adapterResult(AdapterEmployee adapterEmployee);
    }

    public interface Presenter {

        void filter(String nome);

        void createCollaborator();
    }
}
