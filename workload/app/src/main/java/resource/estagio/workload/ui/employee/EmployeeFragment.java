package resource.estagio.workload.ui.employee;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.admin.HistoricResultAdmin.ResultHistoricFragment;
import resource.estagio.workload.ui.admin.HomeAdminContract;
import resource.estagio.workload.ui.employee.adapterEmployee.AdapterEmployee;
import resource.estagio.workload.ui.employee.adapterEmployee.RecyclerItemClickListener;

public class EmployeeFragment extends Fragment implements EmployeeContract.View {

    private EditText editTextEmployee;
    private RecyclerView recyclerViewEmployee;
    private List<TimeEntryModel> list = new ArrayList<>();
    private View view;
    private Button buttonNameEmployee;
    private Button buttonReEmployee;
    private HomeAdminContract.View viewHome;
    private EmployeeContract.Presenter presenter;


    public EmployeeFragment(HomeAdminContract.View view) {
        viewHome = view;
        presenter = new EmployeePresenter(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_employee, container, false);
        loadUI();
        presenter.createCollaborator();
        clickRecycler();

        editTextEmployee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.filter(s.toString().trim());

            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
                                        .replace(R.id.frame_admin, new ResultHistoricFragment(viewHome))
                                        .addToBackStack(null)
                                        .commit();
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
        buttonNameEmployee = view.findViewById(R.id.button_name_employee);
        buttonReEmployee = view.findViewById(R.id.button_re_employee);
        recyclerViewEmployee = view.findViewById(R.id.recyclerview_employee);
        editTextEmployee = view.findViewById(R.id.edit_text_employee);
        presenter = new EmployeePresenter(this);

    }

    @Override
    public void adapterResult(AdapterEmployee adapterEmployee) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEmployee.setLayoutManager(layoutManager);
        recyclerViewEmployee.setHasFixedSize(true);
        recyclerViewEmployee.setAdapter(adapterEmployee);
    }
}


