package resource.estagio.workload.ui.admin.project.add_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.ActivityTypeModel;
import resource.estagio.workload.domain.Customer;
import resource.estagio.workload.ui.admin.HomeAdminContract;



public class AddProjectFragment extends Fragment implements AddProjectContract.View {

    View view;
    private EditText nameProject;
    private EditText demandNumber;
    private Spinner spinnerProjectType;
    private Button buttonProjectConfirm;
    private Customer customer;
    private HomeAdminContract.View activityView;
    private AddProjectContract.Presenter presenter;
    private ActivityModel project;


    public AddProjectFragment(Customer customer, HomeAdminContract.View activityView) {
        this.customer = customer;
        this.activityView = activityView;
        presenter = new AddProjectPresenter(this, activityView);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_project, container, false);
        loadUI();
        if(getArguments().getSerializable("project") != null){
            project = (ActivityModel) getArguments().getSerializable("project");
            nameProject.setText(project.getName());
            demandNumber.setText(project.getDemandNumber());
        }

        presenter.loadSpinner();
        selectItemInSpinner();

        buttonProjectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(project == null ) {
                    presenter.addProject(nameProject.getText().toString(),
                            demandNumber.getText().toString(), customer);
                }
                else{
                    presenter.updateProject(project.getId(), nameProject.getText().toString(),
                            demandNumber.getText().toString(), customer);
                }

            }
        });


        return view;
    }

    private void loadUI() {
        nameProject = view.findViewById(R.id.edit_text_name_project);
        demandNumber = view.findViewById(R.id.edit_text_demand_number_project);
        spinnerProjectType = view.findViewById(R.id.spinner_type_activity_project);
        buttonProjectConfirm = view.findViewById(R.id.button_project_confirm);
    }


    private void selectItemInSpinner() {
        spinnerProjectType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getItemInSpinner(parent, position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void showToast(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void spinnerList(List<ActivityTypeModel> value) {
        ArrayAdapter<ActivityTypeModel> activityTypeModelArrayAdapter
                = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, value);
        spinnerProjectType.setAdapter(activityTypeModelArrayAdapter);
    }
}
