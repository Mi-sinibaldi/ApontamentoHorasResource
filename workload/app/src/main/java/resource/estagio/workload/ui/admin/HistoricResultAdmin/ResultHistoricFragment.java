package resource.estagio.workload.ui.admin.HistoricResultAdmin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.admin.HomeAdminContract;
import resource.estagio.workload.ui.employee.EmployeeFragment;

public class ResultHistoricFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private TextView textTitle, textSubtitle;
    private Fragment fragment;
    private HomeAdminContract.View viewHome;
    private ImageView imageViewBackCollaborator;

    public ResultHistoricFragment(HomeAdminContract.View view) {
        viewHome = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result_historic, container, false);

        loadUI();
        actionTabSelected();
        loadFragment(fragment);
        backToCustomers();

        return view;
    }

    private void actionTabSelected() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        textTitle.setText("Resultado");
                        textSubtitle.setText("Resultado dos Apontamentos do mês");
                        fragment = new ResultFragment(viewHome);
                        break;

                    case 1:
                        textTitle.setText("Histórico");
                        textSubtitle.setText("Histórico de Apontamentos");
                        fragment = new HistoricFragment(viewHome);
                        break;
                }
                loadFragment(fragment);
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadUI() {
        textTitle = view.findViewById(R.id.textTitle);
        textSubtitle = view.findViewById(R.id.textSubtitle);
        fragment = new ResultFragment(viewHome);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicator(0);
        imageViewBackCollaborator = view.findViewById(R.id.image_view_back_collaborator);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void backToCustomers() {

        imageViewBackCollaborator.setOnClickListener(v -> Objects.requireNonNull(getActivity())
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_admin, new EmployeeFragment(viewHome))
                .commit());

        imageViewBackCollaborator.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity())
            .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_admin, new EmployeeFragment(viewHome))
                    .commit();
            viewHome.enableNavigation(false);
        });
    }

}
