package resource.estagio.workload.ui.admin.historicResult;


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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import resource.estagio.workload.infra.ConstantApp;
import resource.estagio.workload.R;
import resource.estagio.workload.ui.admin.historicResult.historic.HistoricFragment;
import resource.estagio.workload.ui.admin.historicResult.result.ResultFragment;
import resource.estagio.workload.ui.admin.HomeAdminContract;
import resource.estagio.workload.ui.admin.employee.EmployeeFragment;

public class ResultHistoricFragment extends Fragment implements ResultHistoricContract.mainResult {

    private View view;
    private TabLayout tabLayout;
    private TextView textTitle, textSubtitle;
    private Fragment fragment;
    private HomeAdminContract.View viewHome;
    private ImageView imageViewBackCollaborator;
    private ResultHistoricContract.mainResult mainResult = this;
    private int month = 0;
    private int year = 0;
    private String textMonth = "";
    private Calendar calendar;

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
        verifyMonth();
        return view;
    }

    private void verifyMonth() {
        if (textMonth.isEmpty()) {
            textSubtitle.setText(ConstantApp.SUBTITLE_RESULT_MAIN_FRAGMENT +
                    new SimpleDateFormat("MMM").format(calendar.getTime()));
        }
    }

    private void actionTabSelected() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        textTitle.setText(ConstantApp.TITLE_RESULT_MAIN_FRAGMENT);
                        textSubtitle.setText(ConstantApp.SUBTITLE_RESULT_MAIN_FRAGMENT + textMonth);
                        fragment = new ResultFragment(viewHome, month, year);
                        break;

                    case 1:
                        textTitle.setText(ConstantApp.TITLE_HISTORIC_MAIN_FRAGMENT);
                        textSubtitle.setText(ConstantApp.SUBTITLE_HISTORIC_MAIN_FRAGMENT);
                        fragment = new HistoricFragment(viewHome, mainResult);
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
        calendar = Calendar.getInstance();
        calendar.get(Calendar.MONTH);
        textTitle = view.findViewById(R.id.textTitle);
        textSubtitle = view.findViewById(R.id.textSubtitle);
        fragment = new ResultFragment(viewHome, month, year);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicator(0);
        imageViewBackCollaborator = view.findViewById(R.id.image_view_back_collaborator);
        textTitle.setText(ConstantApp.TITLE_RESULT_MAIN_FRAGMENT);
        textSubtitle.setText(ConstantApp.SUBTITLE_RESULT_MAIN_FRAGMENT + textMonth);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void backToCustomers() {

        imageViewBackCollaborator.setOnClickListener(v -> {
            getActivity().onBackPressed();
            viewHome.enableNavigation(false);
        });
    }

    @Override
    public void getDateResultFragment(int m, int y, String textMonth) {
        //Toast.makeText(getContext(), "" + m + "/" + y, Toast.LENGTH_SHORT).show();
        month = m;
        year = y;
        this.textMonth = textMonth;
    }
}
