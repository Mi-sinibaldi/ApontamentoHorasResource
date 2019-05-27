package resource.estagio.workload.ui.client;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import resource.estagio.workload.ui.admin.project.ProjectFragment;


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
    private ProgressBar progressBarClient;

    private List<CustomerModel> customerModels;
    private List<CustomerModel> customerModelsDelete;

    private AdapterClient.AdapterInterface adapterInterface;

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

        loadUI();
        loadAdapterListener();

        buttonConstraintLayout.setOnClickListener(v -> Toast.makeText(view.getContext(), "Deu",
                Toast.LENGTH_SHORT).show());

        presenter.getCustomers(true);

        imageViewConfigClient.setOnClickListener(v -> presenter.getCustomers(false));

        buttonSaveClient.setOnClickListener( v -> {
            if(customerModelsDelete.size() > 0)
                presenter.deleteCustomer(customerModelsDelete);
            else
                presenter.getCustomers(true);

        });

        textViewCancelClient.setOnClickListener( v -> presenter.getCustomers(true));

    }

    private void loadUI() {
        presenter = new ClientPresenter(this);
        buttonConstraintLayout = view.findViewById(R.id.button_constraints_client);
        recyclerClient = view.findViewById(R.id.recycler_clients_client);
        textViewCancelClient = view.findViewById(R.id.text_view_cancel_client);
        textViewSelecionarClient = view.findViewById(R.id.text_view_selecionar_client);
        imageViewConfigClient = view.findViewById(R.id.image_view_config_client);
        buttonSaveClient = view.findViewById(R.id.button_save_client);
        progressBarClient = view.findViewById(R.id.progress_client);
    }

    private  void loadAdapterListener(){
        customerModelsDelete = new ArrayList<>();
        adapterInterface = new AdapterClient.AdapterInterface() {

            @Override
            public void removeClient(View v, int position) {
                customerModelsDelete.add(customerModels.get(position));
            }

            @Override
            public void goToProject(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("customer", customerModels.get(position));
                ProjectFragment fragment = new ProjectFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_admin, fragment).commit();
            }
        };
    }


    @Override
    public void setRecyclerClient(List<CustomerModel> customerModels, boolean status){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerClient.setLayoutManager(layoutManager);
        recyclerClient.setHasFixedSize(true);
        this.customerModels = customerModels;
        showAdapterRecycler(status);
    }

    @Override
    public void notification(String messenge) {
        Toast.makeText(getApplicationContext(), messenge, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAdapterRecycler(boolean status) {
        if(status){
            imageViewConfigClient.setVisibility(View.VISIBLE);
            textViewSelecionarClient.setVisibility(View.VISIBLE);
            textViewCancelClient.setVisibility(View.INVISIBLE);
            buttonSaveClient.setVisibility(View.INVISIBLE);
        }else {
            imageViewConfigClient.setVisibility(View.INVISIBLE);
            textViewSelecionarClient.setVisibility(View.INVISIBLE);
            textViewCancelClient.setVisibility(View.VISIBLE);
            buttonSaveClient.setVisibility(View.VISIBLE);
        }
        adapterClient = new AdapterClient(customerModels, status, adapterInterface);
        recyclerClient.setAdapter(adapterClient);
    }

    private Context getApplicationContext() {
        return view.getContext();
    }

    @Override
    public void showProgressClient(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        recyclerClient.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        progressBarClient.setVisibility(show ? View.VISIBLE : View.GONE);
        progressBarClient.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBarClient.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

    }
}
