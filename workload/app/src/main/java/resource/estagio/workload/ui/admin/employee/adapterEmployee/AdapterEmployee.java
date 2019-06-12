package resource.estagio.workload.ui.admin.employee.adapterEmployee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.EmployeeModel;

public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.MyViewHolder> {

    private List<EmployeeModel> listEmployee;
    private int sizeList;
    private AdapterEmployeeInterface listerner;

    public AdapterEmployee(List<EmployeeModel> listEmployee, AdapterEmployeeInterface listerner) {
        this.listEmployee = listEmployee;
        this.listerner = listerner;
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
        holder.nome.setText(model.getName());
        holder.re.setText(model.getRe());

        if (sizeList == holder.getLayoutPosition())
            holder.constraintEmployee.setPadding(0, 0, 0, 100);
        else
            holder.constraintEmployee.setPadding(0, 0, 0, 0);

        holder.constraintEmployee.setOnClickListener(v -> listerner.goToResult(v, position,
                listEmployee.get(holder.getLayoutPosition())));
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public void filterAdapter(List<EmployeeModel> filterList) {
        listEmployee = filterList;
        notifyDataSetChanged();
    }

    public interface AdapterEmployeeInterface {

        public void goToResult(View v, int position, EmployeeModel model);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView re;
        ConstraintLayout constraintEmployee;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.text_name_employee);
            re = itemView.findViewById(R.id.text_re_employee);
            constraintEmployee = itemView.findViewById(R.id.constraint_employee);
        }
    }
}
