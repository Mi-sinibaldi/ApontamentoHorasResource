package resource.estagio.workload.ui.admin.employee;

import android.content.Context;

import resource.estagio.workload.ui.admin.employee.adapterEmployee.AdapterEmployee;

public class EmployeeContract {

    public interface View {

        void adapterResult(AdapterEmployee adapterEmployee);

        void showDialog(String message, boolean status);

        Context getActivity();

        void showProgress(boolean b);
    }

    public interface Presenter {

        void filter(String nome);

        void listEmployee();
    }
}
