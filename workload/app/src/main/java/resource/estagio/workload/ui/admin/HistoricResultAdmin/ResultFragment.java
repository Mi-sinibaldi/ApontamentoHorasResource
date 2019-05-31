package resource.estagio.workload.ui.admin.HistoricResultAdmin;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.admin.HistoricResultAdmin.adapter.AdapterListResult;
import resource.estagio.workload.ui.admin.HomeAdminContract;


public class ResultFragment extends Fragment implements ResultHistoricContract.ResultView {

    private AdapterListResult adapterListResult;
    private RecyclerView recyclerViewListResult;
    private List<newListResultAdmin> newlistResult;
    private ResultHistoricContract.ResultPresenter presenter;
    private View view;
    private Dialog dialog;
    private Button buttonError;
    private TextView text_dialog_error;
    private HomeAdminContract.View viewHome;
    private SwipeRefreshLayout swipe_Refresh_result;


    public ResultFragment(HomeAdminContract.View viewHome) {
        this.viewHome = viewHome;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);

        loadUi();
        actioSwipeRefresh(this);
        presenter.getListResult(5, 2019);

        return view;
    }

    private void actioSwipeRefresh(ResultHistoricContract.ResultView view) {
        recyclerViewListResult.setVisibility(View.GONE);
        swipe_Refresh_result.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter = new ResultPresenter(view);
                presenter.getListResult(5, 2019);
            }
        });
    }

    private void loadUi() {
        recyclerViewListResult = view.findViewById(R.id.recyclerView_result_admin);
        swipe_Refresh_result = view.findViewById(R.id.swipe_Refresh_result);
        presenter = new ResultPresenter(this);
    }


    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListResult.setLayoutManager(layoutManager);
        recyclerViewListResult.setHasFixedSize(true);

        adapterListResult = new AdapterListResult(newlistResult);
        recyclerViewListResult.setAdapter(adapterListResult);

    }

    @Override
    public void dialog(boolean Key) {
        recyclerViewListResult.setVisibility(Key ? View.INVISIBLE : View.VISIBLE);
        swipe_Refresh_result.setRefreshing(Key);

        viewHome.enableNavigation(Key);
    }

    @Override
    public void showListResult(List<newListResultAdmin> list) {
        newlistResult = list;
        configAdapter();

        swipe_Refresh_result.setRefreshing(false);

    }

    @Override
    public void showErrorMessage(String text) {
        showDialogError(text);
    }

    @Override
    public void showSucessMessage(String text) {

    }


    private void showDialogError(String text) {

        dialog = new Dialog(getActivity(), R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.acativity_dialog_error);
        dialog.setCancelable(false);

        text_dialog_error = dialog.findViewById(R.id.text_dialog_error);
        text_dialog_error.setText(text);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
        buttonError = dialog.findViewById(R.id.button_dialog_error);
        buttonError.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}
