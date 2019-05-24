package resource.estagio.workload.ui.client;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.CustomerModel;


public class ClientFragment extends Fragment implements ClientContract.View {

    private ClientPresenter presenter;
    private View view;
    private RecyclerView recyclerClient;
    private ConstraintLayout buttonConstraintLayout;
    private ImageView imageViewConfigClient;
    private Button buttonSaveClient;
    private TextView textViewCancelClient;
    private TextView textViewSelecionarClient;
    private AdapterClient adapterClient;

    private List<CustomerModel> customerModels;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        presenter= new ClientPresenter(this);
        loadUI();
        buttonConstraintLayout = view.findViewById(R.id.button_constraints_client);
        buttonConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Deu", Toast.LENGTH_SHORT).show();
            }
        });

        presenter.getCustomers();

        imageViewConfigClient.setOnClickListener(v -> presenter.setConfig());

        buttonSaveClient.setOnClickListener(v -> presenter.setReturn());

        textViewCancelClient.setOnClickListener(v -> presenter.setReturn());

    }

    public void loadUI(){
        buttonSaveClient = view.findViewById(R.id.button_save_client);
        textViewCancelClient = view.findViewById(R.id.text_view_cancel_client);
        textViewSelecionarClient = view.findViewById(R.id.text_view_selecionar_client);
        recyclerClient = view.findViewById(R.id.recycler_clients_client);
        imageViewConfigClient = view.findViewById(R.id.image_view_config_client);

    }

    @Override
    public void setRecyclerClient(List<CustomerModel> customerModels){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerClient.setLayoutManager(layoutManager);
        recyclerClient.setHasFixedSize(true);
        this.customerModels = customerModels;
        adapterClient = new AdapterClient(customerModels,true);
        recyclerClient.setAdapter(adapterClient);

    }

    @Override
    public void notification(String messenge) {
        Toast.makeText(getApplicationContext(), messenge, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRemove() {
        imageViewConfigClient.setVisibility(View.INVISIBLE);
        textViewSelecionarClient.setVisibility(View.INVISIBLE);
        textViewCancelClient.setVisibility(View.VISIBLE);
        buttonSaveClient.setVisibility(View.VISIBLE);
        adapterClient = new AdapterClient(customerModels,false);
        recyclerClient.setAdapter(adapterClient);
       // adapterClient.myViewHolder.setIconRemove();
    }

    @Override
    public void showReturn() {
        imageViewConfigClient.setVisibility(View.VISIBLE);
        textViewSelecionarClient.setVisibility(View.VISIBLE);
        textViewCancelClient.setVisibility(View.INVISIBLE);
        buttonSaveClient.setVisibility(View.INVISIBLE);
        adapterClient = new AdapterClient(customerModels,true);
        recyclerClient.setAdapter(adapterClient);
      //  adapterClient.myViewHolder.setIconClient();
    }

    private Context getApplicationContext() {
        return view.getContext();
    }
}
