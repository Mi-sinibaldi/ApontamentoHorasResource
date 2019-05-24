package resource.estagio.workload.ui.employee;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.EmployeeAPI;
import resource.estagio.workload.data.remote.model.EmployeeModel;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.client.ClientFragment;
import resource.estagio.workload.ui.employee.adapterEmployee.AdapterEmployee;
import resource.estagio.workload.ui.employee.adapterEmployee.RecyclerItemClickListener;
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
    private Button button_re_employee;
    private List<EmployeeModel> employee = new ArrayList<>();
    private ProgressBar progress_employee;


    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employee, container, false);
        loadUI();
        criarColaborador();
        showListTimeline(null);
        clickRecycler();
        return view;

    }

    private void clickRecycler() {
        id_recyclerview_employee.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        id_recyclerview_employee,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.frame_admin, new ClientFragment()).commit();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        }

                )
        );
    }

    private void loadUI() {
        button_name_employee = view.findViewById(R.id.button_name_employee);
        button_re_employee = view.findViewById(R.id.button_re_employee);
        id_recyclerview_employee = view.findViewById(R.id.id_recyclerview_employee);
        //progress_employee = view.findViewById(R.id.progress_employee);

    }


    public void showProsseEmployee(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        id_recyclerview_employee.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        id_recyclerview_employee.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                id_recyclerview_employee.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

        progress_employee.setVisibility(show ? View.VISIBLE : View.GONE);
        progress_employee.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progress_employee.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
    }

    public void showListTimeline(List<TimeEntryModel> list) {
        //listWorkLoad = list;
        configAdapter();
    }

    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        id_recyclerview_employee.setLayoutManager(layoutManager);
        id_recyclerview_employee.setHasFixedSize(true);
        AdapterEmployee adapterEmployee = new AdapterEmployee(employee);
        id_recyclerview_employee.setAdapter(adapterEmployee);
    }

    public void criarColaborador() {
        EmployeeModel employeeModel = new EmployeeModel("Michelle Sinibaldi", "re037933");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Elivel Nascimento", "re037926");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Gabriella Aleo", "re037929");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Guilherme Matos", "re037954");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Guilherme Sassa", "re024242");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Gerson Junior", "re0379534");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Catia Souza", "re0379545");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Mateus Matos", "re0379523");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Marcus Galdino", "re0379501");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Igor Oliveira", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Andrey Little", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Gabriel Couto", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Lucas Balloon", "re036748");
        employee.add(employeeModel);

        employeeModel = new EmployeeModel("Paulo Henrique", "re032398");
        employee.add(employeeModel);
    }
}
