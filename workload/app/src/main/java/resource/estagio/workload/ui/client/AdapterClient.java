package resource.estagio.workload.ui.client;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.CustomerModel;

public class AdapterClient extends RecyclerView.Adapter<AdapterClient.MyViewHolder>{

    List<CustomerModel> list;

    public AdapterClient(List<CustomerModel> list) {
        this.list = list;
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
        holder.buttonCardClient.setText(customerModel.getName());

    }

    @Override
    public int getItemCount() {
        if( list == null)
            return 0;
        else
            return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button buttonCardClient;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            buttonCardClient=itemView.findViewById(R.id.button_card_client);
        }
    }
}
