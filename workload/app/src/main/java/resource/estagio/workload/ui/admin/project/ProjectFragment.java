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
import resource.estagio.workload.infra.Repository;
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
    ProgressBar progressBarTimeLine;

    public ProjectFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_project_admin, container, false);
        loadUI();
        setCustomerName();
        loadList();
        return view;
    }

    private void loadList() {

        new ActivityRepository().getActivity(1, App.getUser().getAccessToken(),
                new BaseCallback<List<ActivityModel>>() {
                    @Override
                    public void onSuccessful(List<ActivityModel> value) {
                        new ActivityRepository().getActivity(3, App.getUser().getAccessToken(),
                                new BaseCallback<List<ActivityModel>>() {
                                    @Override
                                    public void onSuccessful(List<ActivityModel> value) {
                                        if(value==null) {
                                            Toast.makeText(getActivity(),
                                                    "Nenhum registro encontrado",
                                                    Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                        AdapterProject adapter = new AdapterProject(value);
                                        recyclerProject.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onUnsuccessful(String error) {
                                        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                                    }
                                });
                    }

                    @Override
                    public void onUnsuccessful(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                    }
                });
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

}
