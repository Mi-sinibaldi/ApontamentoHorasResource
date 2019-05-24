package resource.estagio.workload.ui.employee;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.employee.adapterEmployee.AdapterEmployee;
import resource.estagio.workload.ui.timeline.TimelineFragment;
import resource.estagio.workload.ui.timeline.adapterTimeLine.AdapterTimeline;

public class EmployeeFragment extends Fragment {

    private EmployeeFragment employeeFragment;
    private ImageView imageView_recource;
    private TextView text_employee, textView_desc;
    private SearchView searchView;
    private RecyclerView id_recyclerview_employee;
    private List<TimeEntryModel> list = new ArrayList<>();
    private View view;
    private List<TimeEntryModel> listWorkLoad;
    private Button button_name_employee;
    private  Button button_re_employee;


    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employee, container, false);
        loadUI();
        return view;
    }

    private void loadUI() {
        button_name_employee  = view.findViewById(R.id.button_name_employee);
        button_re_employee = view.findViewById(R.id.button_re_employee);

    }

    public void showListTimeline(List<TimeEntryModel> list) {
        listWorkLoad = list;
        configAdapter();
    }

    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        id_recyclerview_employee.setLayoutManager(layoutManager);
        id_recyclerview_employee.setHasFixedSize(true);
        AdapterEmployee adapterEmployee = new AdapterEmployee(null);
        id_recyclerview_employee.setAdapter(adapterEmployee);
    }
}
