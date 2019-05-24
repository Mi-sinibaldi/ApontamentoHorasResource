package resource.estagio.workload.ui.admin.project.adapterProject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.ActivityModel;

public class AdapterProject extends RecyclerView.Adapter<AdapterProject.MyViewHolder> {


    private List<ActivityModel> listProject;

    public AdapterProject(List<ActivityModel> listProject) {
        this.listProject = listProject;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_adapter_project, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ActivityModel activityModel = listProject.get(position);

        holder.activityName.setText(activityModel.getName());
    }

    @Override
    public int getItemCount() {
        return listProject.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView activityName;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            activityName = itemView.findViewById(R.id.text_view_name_project);
        }
    }
}
