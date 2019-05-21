package resource.estagio.workload.ui.timeline.adapterTimeLine;

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

public class AdapterTimeline extends RecyclerView.Adapter<AdapterTimeline.MyViewHolder> {

    private List<TimeEntryModel> listTimeline;
    private int sizeList;

    public AdapterTimeline(List<TimeEntryModel> listTimeline) {
        this.listTimeline = listTimeline;
        Collections.reverse(listTimeline);
        sizeList = listTimeline.size() - 1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_adapter_timeline, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TimeEntryModel model = listTimeline.get(position);

        if (position == sizeList) {
            holder.activityName.setText(model.getActivityName());
            holder.customerName.setText(model.getCustomerName());
            holder.date.setText(model.getDate().substring(0, 5));
            holder.hours.setText(model.getHours() + " Horas");
            holder.reason.setText(model.getReason());
            holder.line.setVisibility(View.INVISIBLE);
        } else {
            holder.activityName.setText(model.getActivityName());
            holder.customerName.setText(model.getCustomerName());
            holder.date.setText(model.getDate().substring(0, 5));
            holder.hours.setText(model.getHours() + " Horas");
            holder.reason.setText(model.getReason());
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return listTimeline.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView activityName;
        TextView customerName;
        TextView hours;
        TextView date;
        TextView reason;
        View line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.textView_name_project);
            customerName = itemView.findViewById(R.id.button_name_customer);
            hours = itemView.findViewById(R.id.button_hours);
            date = itemView.findViewById(R.id.textView_date_history);
            reason = itemView.findViewById(R.id.textView_reason_history);
            line = itemView.findViewById(R.id.view5);

        }
    }
}
