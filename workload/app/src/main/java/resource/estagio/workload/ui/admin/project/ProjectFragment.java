package resource.estagio.workload.ui.admin.project;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.ui.admin.HomeAdminContract;
import resource.estagio.workload.ui.admin.project.adapterProject.AdapterProject;
import resource.estagio.workload.ui.client.ClientFragment;

public class ProjectFragment extends Fragment implements ProjectContract.View {

    private View view;
    private RecyclerView recyclerProject;
    private TextView textViewCustomer;
    private ImageView imageViewBackCustomers;
    private Button buttonNewProject;
    private ProgressBar progressBarProjectAdmin;
    private ProjectContract.Presenter presenter;
    private CustomerModel customer;
    private HomeAdminContract.View activityView;

    public ProjectFragment(HomeAdminContract.View activityView) {
        this.activityView = activityView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_project_admin, container, false);

        presenter = new ProjectPresenter(this);
        Bundle arguments = getArguments();
        customer = (CustomerModel) arguments.getSerializable("customer");
        loadUI();
        setCustomerName();
        presenter.loadList(customer.getId());
        backtoCustomers();
        return view;
    }

    private void backtoCustomers() {

        imageViewBackCustomers.setOnClickListener(v -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_admin, new ClientFragment(activityView))
                .commit());
    }

    private void setCustomerName() {
        textViewCustomer.setText(customer.getName());
    }

    private void loadUI() {

        recyclerProject = view.findViewById(R.id.recycler_project);
        textViewCustomer = view.findViewById(R.id.text_view_customer_project);
        imageViewBackCustomers = view.findViewById(R.id.image_view_back_customers);
        progressBarProjectAdmin = view.findViewById(R.id.progress_bar_project_admin);
        //buttonNewProject = view.findViewById(R.id.button_new_project);
    }

    @Override
    public void listAdapter(AdapterProject adapter) {
        recyclerProject.setAdapter(adapter);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getActivity(),
                String.valueOf(message),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),
                String.valueOf(error),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(boolean result) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        recyclerProject.setVisibility(result ? View.GONE : View.VISIBLE);
        progressBarProjectAdmin.setVisibility(result ? View.VISIBLE : View.GONE);
        progressBarProjectAdmin.animate().setDuration(shortAnimTime).alpha(
                result ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBarProjectAdmin.setVisibility(result ? View.VISIBLE : View.GONE);
            }
        });
        activityView.enableNavigation(result);

    }
}

