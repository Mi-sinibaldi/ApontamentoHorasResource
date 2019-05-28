package resource.estagio.workload.ui.admin.project.adapterProject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.loadFilds(activityModel);
    }

    @Override
    public int getItemCount() {
        return listProject.size();
    }

    public void showIconActions() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView activityName;
        TextView textRenameProject;
        ImageView ImageDeleteProject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.text_view_name_project);
            textRenameProject = itemView.findViewById(R.id.text_view_rename_project);
            ImageDeleteProject = itemView.findViewById(R.id.image_view_delete_project);
        }

        public void loadFilds(ActivityModel activityModel) {
            activityName.setText(activityModel.getName());
        }
    }
}
