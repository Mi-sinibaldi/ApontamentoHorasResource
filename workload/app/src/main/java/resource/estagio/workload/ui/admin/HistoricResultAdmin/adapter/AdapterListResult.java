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

public class AdapterListResult extends RecyclerView.Adapter<AdapterListResult.MyViewHolder>{

    private List<TimeEntryModel> listTimeline;
    private int sizeList;

    public AdapterListResult(List<TimeEntryModel> listTimeline) {
        this.listTimeline = listTimeline;
        Collections.reverse(listTimeline);
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
        TimeEntryModel model = listTimeline.get(position);

        holder.projectName.setText("AlfaPrev");
        holder.customerName.setText("Banco Alfa");
        holder.Allhours.setText("40:00");
        holder.hours.setText("20:00");

    }

    @Override
    public int getItemCount() {
        return 5;
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
