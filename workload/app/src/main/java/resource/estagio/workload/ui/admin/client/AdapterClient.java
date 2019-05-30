package resource.estagio.workload.ui.admin.client;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.CustomerModel;

public class AdapterClient extends RecyclerView.Adapter<AdapterClient.MyViewHolder>{

    private List<CustomerModel> list;

    private AdapterInterface listener;

    private boolean setStatusList;

    public AdapterClient(List<CustomerModel> list, boolean status, AdapterInterface listener) {
        this.list = list;
        this.setStatusList = status;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_adapter_client,parent,false);
      return new MyViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CustomerModel customerModel = list.get(position);
        holder.textViewClientAdapter.setText(customerModel.getName());
        if(setStatusList) {
            holder.buttonCardClient.setOnClickListener(v -> listener.goToProject(v, position));
            holder.setIconClient();
        }else{
            holder.imageViewDeleteClient.setOnClickListener(v -> {
                listener.removeClient(v, position);

                list.remove(position);
                notifyItemRemoved(position);

            });
            holder.setIconRemove();
        }
    }

    @Override
    public int getItemCount() {
        if( list == null)
            return 0;
        else
            return list.size();
    }

    public interface AdapterInterface {
        void removeClient(View v, int position);

        void goToProject(View v, int position);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout buttonCardClient;
        TextView textViewClientAdapter;
        ImageView imageViewIconClientAdapter;
        ImageView imageViewDeleteClient;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClientAdapter = itemView.findViewById(R.id.text_view_client_adapter);
            buttonCardClient = itemView.findViewById(R.id.button_card_client);
            imageViewIconClientAdapter = itemView.findViewById(R.id.image_view_icon_adapter_client);
            imageViewDeleteClient = itemView.findViewById(R.id.image_view_delete_client);
        }

       void setIconRemove() {
            imageViewDeleteClient.setVisibility(View.VISIBLE);
            imageViewIconClientAdapter.setVisibility(View.INVISIBLE);
        }

        void setIconClient() {
            imageViewDeleteClient.setVisibility(View.INVISIBLE);
            imageViewIconClientAdapter.setVisibility(View.VISIBLE);
        }
    }
}


