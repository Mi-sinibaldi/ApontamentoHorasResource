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
import resource.estagio.workload.data.remote.model.TimeEntryModel;

public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.MyViewHolder> {

    private List<TimeEntryModel> listEmployee;
    private  int sizeList;

    public  AdapterEmployee(List<TimeEntryModel>listEmployee){
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
        TimeEntryModel model = listEmployee.get(position);

        if(position == sizeList){

        }
    }

      @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

      Button activityName;
      Button activityId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.button_name_employee);
            activityId = itemView.findViewById(R.id.button_re_employee);

        }
    }
}
