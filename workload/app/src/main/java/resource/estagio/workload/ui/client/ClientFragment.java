package resource.estagio.workload.ui.client;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import resource.estagio.workload.ui.admin.project.ProjectFragment;


public class ClientFragment extends Fragment implements ClientContract.View {

    private ClientPresenter presenter;
    private View view;
    private RecyclerView recyclerClient;
    private ConstraintLayout buttonConstraintLayout;

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
        buttonConstraintLayout = view.findViewById(R.id.button_constraints_client);
        buttonConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Deu", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_admin, new ProjectFragment()).commit();
            }
        });

        recyclerClient = view.findViewById(R.id.recycler_clients_client);
        presenter.getCustomers();

    }

    @Override
    public void setRecyclerClient(List<CustomerModel> customerModels){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerClient.setLayoutManager(layoutManager);
        recyclerClient.setHasFixedSize(true);

        AdapterClient adapterClient = new AdapterClient(customerModels);
        recyclerClient.setAdapter(adapterClient);

    }

    @Override
    public void notification(String messenge) {
        Toast.makeText(getApplicationContext(), messenge, Toast.LENGTH_SHORT).show();
    }

    private Context getApplicationContext() {
        return view.getContext();
    }
}
