package resource.estagio.workload.ui.admin.HistoricResultAdmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.admin.HistoricResultAdmin.newListResultAdmin;

public class AdapterListResult extends RecyclerView.Adapter<AdapterListResult.MyViewHolder>{

    private List<newListResultAdmin> listTimeline;
    private int sizeList;

    public AdapterListResult(List<newListResultAdmin> listTimeline) {
        this.listTimeline = listTimeline;
        //Collections.reverse(listTimeline);
        sizeList = listTimeline.size() - 1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_adapter_result_admin, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        newListResultAdmin model = listTimeline.get(position);
        String project="";
        String hours="";
        String[] split = model.getProjectName().split("!");
        for (int i =0;i<split.length-1;i++){
            if(i%2==0) {
                project += split[i] + "\n";
                hours += split[i + 1] + "\n";
            }
        }
        holder.projectName.setText(project);
        holder.customerName.setText(model.getCustomerName());

        holder.hours.setText(hours);

    }

    @Override
    public int getItemCount() {
        return listTimeline.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView projectName;
        TextView customerName;
        TextView Allhours;
        TextView hours;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.textView_name_project_result);
            customerName = itemView.findViewById(R.id.textView_name_customer_result);
            Allhours = itemView.findViewById(R.id.button_all_hours_result);
            hours = itemView.findViewById(R.id.textView_hour_project_result);
        }
    }
}
