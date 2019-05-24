package resource.estagio.workload.ui.admin;

import android.view.MenuItem;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.SettingsFragment;
import resource.estagio.workload.ui.client.ClientFragment;
import resource.estagio.workload.ui.employee.EmployeeFragment;

public class HomeAdminPresenter implements HomeAdminContract.Presenter {
    private HomeAdminContract.View view;

    public HomeAdminPresenter(HomeAdminContract.View view) {
        this.view = view;
    }

    @Override
    public void identifyItemClicked(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ic_employee_admin:
                view.showFragment(new EmployeeFragment());
                break;
            case R.id.ic_customer_admin:
                view.showFragment(new ClientFragment());
                break;
            case R.id.ic_config_admin:
                view.showFragment(new SettingsFragment());
                break;
            case R.id.ic_exit_admin:
                view.showDialogChooser();
                break;
        }
    }
}
