package resource.estagio.workload.ui.employee;



public class EmployeePresenter implements EmployeeContract.Presenter {

    private EmployeeContract.View view;

    public EmployeePresenter(EmployeeContract.View view) {
        this.view = view;
    }



}
