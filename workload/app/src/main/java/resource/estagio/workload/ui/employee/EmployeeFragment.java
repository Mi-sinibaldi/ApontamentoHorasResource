package resource.estagio.workload.ui.employee;


import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.EmployeeModel;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.admin.HistoricResultAdmin.ResultHistoricFragment;
import resource.estagio.workload.ui.admin.HomeAdminContract;
import resource.estagio.workload.ui.employee.adapterEmployee.AdapterEmployee;
import resource.estagio.workload.ui.employee.adapterEmployee.RecyclerItemClickListener;

public class EmployeeFragment extends Fragment implements EmployeeContract.View {

    private EmployeeFragment employeeFragment;
    //private ImageView imageView_recource;
    private TextView text_employee, textView_desc;
    private EditText editTextEmployee;
    private RecyclerView recyclerViewEmployee;
    private List<TimeEntryModel> list = new ArrayList<>();
    private View view;
    private List<TimeEntryModel> listWorkLoad;
    private Button buttonNameEmployee;
    private Button buttonReEmployee;
    private List<EmployeeModel> employee = new ArrayList<>();
    private ProgressBar progressEmployee;
    private HomeAdminContract.View viewHome;
    private EmployeeContract.Presenter presenter;
    private AdapterEmployee adapterEmployee;

    public EmployeeFragment(HomeAdminContract.View view) {
        viewHome = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_employee, container, false);
        loadUI();
        createCollaborator();
        clickRecycler();
        configAdapter();

        editTextEmployee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString().trim());

            }
        });
        return view;

    }
    private void clickRecycler() {
        recyclerViewEmployee.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        recyclerViewEmployee,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.frame_admin, new ResultHistoricFragment(viewHome)).commit();
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

    private void filter(String nome) {
        ArrayList<EmployeeModel> filterList = new ArrayList<>();

        for (EmployeeModel colaborador : employee) {
            if (colaborador.getNome().toLowerCase().contains(nome.toLowerCase())) {
                filterList.add(colaborador);
            } else if (colaborador.getRe().toLowerCase().contains(nome.toLowerCase())) {
                filterList.add(colaborador);
            }
        }
        if (filterList == null) adapterEmployee.filterAdapter(employee);
        else adapterEmployee.filterAdapter(filterList);


    }


    private void loadUI() {
        buttonNameEmployee = view.findViewById(R.id.button_name_employee);
        buttonReEmployee = view.findViewById(R.id.button_re_employee);
        recyclerViewEmployee = view.findViewById(R.id.recyclerview_employee);
        editTextEmployee = view.findViewById(R.id.edit_text_employee);
        presenter = new EmployeePresenter(this);

    }


    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEmployee.setLayoutManager(layoutManager);
        recyclerViewEmployee.setHasFixedSize(true);
        adapterEmployee = new AdapterEmployee(employee);
        recyclerViewEmployee.setAdapter(adapterEmployee);
    }

    public void createCollaborator() {
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


