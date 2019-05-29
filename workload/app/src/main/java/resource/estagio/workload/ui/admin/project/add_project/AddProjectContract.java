package resource.estagio.workload.ui.admin.project.add_project;

import android.app.Activity;
import android.content.Context;
import android.widget.AdapterView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityTypeModel;
import resource.estagio.workload.domain.Customer;

public class AddProjectContract {
    
    interface View {

        void showToast(String error);

        void spinnerList(List<ActivityTypeModel> value);

        FragmentActivity getActivity();
    }
    interface Presenter{

        void loadSpinner();

        void getItemInSpinner(AdapterView<?> parent, int position);

        void addProject(String nameProject, String demandNumber, Customer customer);
    }
    
}
