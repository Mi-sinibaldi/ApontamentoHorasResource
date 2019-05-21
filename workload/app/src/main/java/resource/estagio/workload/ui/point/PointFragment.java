package resource.estagio.workload.ui.point;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.infra.DateDialog;
import resource.estagio.workload.infra.InputFilterMinMax;

public class PointFragment extends Fragment implements PointContract.View, DatePickerDialog.OnDateSetListener {

    DecimalFormat f = new DecimalFormat("##00");

    private PointContract.Presenter presenter;

    private Context context;
    private View view;
    private EditText editTextDatePoint;
    private EditText editTextHourPoint;
    private Spinner spinnerCustomerPoint;

    private Calendar date;
    private int customerId;
    private String customerName;


    // Construtor Vazio
    public PointFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        loadUI();
        loadDateHourSave();
        presenter.getCustumers();
    }

    private void loadDateHourSave() {
        Calendar c = Calendar.getInstance();
        setDateEditTextPoint(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        editTextDatePoint.setOnClickListener(v -> {
            DialogFragment datePicker = new DateDialog(this);
            datePicker.show(getChildFragmentManager(), "Escolha o dia");
        });
    }

    private void loadUI() {
        editTextDatePoint = view.findViewById(R.id.edit_text_date_point);
        editTextHourPoint = view.findViewById(R.id.edit_text_hour_point);
        editTextHourPoint.setFilters(new InputFilter[]{new InputFilterMinMax(1,8)});
        spinnerCustomerPoint = view.findViewById(R.id.spinner_customer_point);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point, null);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDateEditTextPoint(year, month, dayOfMonth);
    }

    @SuppressLint("DefaultLocale")
    private void setDateEditTextPoint(int year, int month, int dayOfMonth) {
        date = new GregorianCalendar(year, month, dayOfMonth);
        editTextDatePoint.setText(String.format("%s/%s/%d",
                f.format(date.get(Calendar.DATE)),
                f.format(date.get(Calendar.MONTH) + 1),
                date.get(Calendar.YEAR)));
    }

    @Override
    public void notification(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadSpinnerCustomer(List<CustomerModel> customerModels) {
        ArrayAdapter<CustomerModel> adapterCustomer = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, customerModels);
        adapterCustomer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCustomerPoint.setAdapter(adapterCustomer);
        spinnerCustomerPoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel customerModel = (CustomerModel) parent.getItemAtPosition(position);
                customerId = customerModel.getId();
                customerName = customerModel.getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
