package resource.estagio.workload.ui.employee.adapterEmployee;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Collections;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.EmployeeModel;
import resource.estagio.workload.data.remote.model.TimeEntryModel;

public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.MyViewHolder> implements View.OnClickListener {

    private List<EmployeeModel> listEmployee;
    private int sizeList;

    public AdapterEmployee(List<EmployeeModel> listEmployee) {
        this.listEmployee = listEmployee;
        Collections.reverse(listEmployee);
        sizeList = listEmployee.size() - 1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listEmployee = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_adapter_employee, parent, false);
        return new MyViewHolder(listEmployee);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EmployeeModel model = listEmployee.get(position);
        holder.nome.setText(model.getNome());
        holder.re.setText(model.getRe());
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public void filterAdapter(List<EmployeeModel> filterList){
        listEmployee = filterList;
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button nome;
        Button re;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.button_name_employee);
            re = itemView.findViewById(R.id.button_re_employee);

        }
    }
}
