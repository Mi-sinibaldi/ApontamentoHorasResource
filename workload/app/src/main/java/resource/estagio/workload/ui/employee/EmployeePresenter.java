package resource.estagio.workload.ui.employee;

import androidx.fragment.app.Fragment;

import resource.estagio.workload.data.remote.model.EmployeeModel;

public class EmployeePresenter implements EmployeeContract.Presenter {

    private EmployeeContract.View view;

    public EmployeePresenter(EmployeeContract.View view) {
        this.view = view;
    }



}
