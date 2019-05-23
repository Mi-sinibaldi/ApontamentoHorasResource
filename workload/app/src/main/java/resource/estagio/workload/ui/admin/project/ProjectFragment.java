package resource.estagio.workload.ui.admin.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import resource.estagio.workload.R;

public class ProjectFragment extends Fragment implements ProjectContract.View {

    private View view;
    private TextView textViewCustomer;
    private Button imageViewBackCustomers;
    private Button buttonNewProject;

    ProjectFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_project_admin, container, false);
        loadUI();
        return null;
    }

    private void loadUI() {

        textViewCustomer = view.findViewById(R.id.text_view_customer_project);
        imageViewBackCustomers = view.findViewById(R.id.image_view_back_customers);
        buttonNewProject = view.findViewById(R.id.button_new_project);
    }

}