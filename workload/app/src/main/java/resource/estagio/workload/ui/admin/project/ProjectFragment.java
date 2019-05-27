package resource.estagio.workload.ui.admin.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.data.repository.ActivityRepository;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.ui.admin.project.adapterProject.AdapterProject;
import resource.estagio.workload.ui.timeline.adapterTimeLine.AdapterTimeline;

public class ProjectFragment extends Fragment implements ProjectContract.View {

    private AdapterTimeline adapterListProject;
    private View view;
    private List<ActivityModel> list = new ArrayList<>();
    private List<TimeEntryModel> listProjectLoad;
    private RecyclerView recyclerProject;
    private TextView textViewCustomer;
    private ImageView imageViewBackCustomers;
    private Button buttonNewProject;
    private String activityName;
    private TextView text_dialog_error;
    ProgressBar progressBarProjectAdmin;
    private ProjectContract.Presenter presenter;

    public ProjectFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_project_admin, container, false);

        presenter = new ProjectPresenter(this);
        loadUI();
        setCustomerName();
        presenter.loadList();
        return view;
    }

    private void setCustomerName() {
        textViewCustomer.setText("Banco Alfa");
    }

    private void loadUI() {

        recyclerProject = view.findViewById(R.id.recycler_project);
        textViewCustomer = view.findViewById(R.id.text_view_customer_project);
        imageViewBackCustomers = view.findViewById(R.id.image_view_back_customers);
        //buttonNewProject = view.findViewById(R.id.button_new_project);
    }

    @Override
    public void listAdapter(AdapterProject adapter) {
        recyclerProject.setAdapter(adapter);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getActivity(),
                String.valueOf(message),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),
                String.valueOf(error),
                Toast.LENGTH_LONG).show();
    }
}

