package resource.estagio.workload.ui.admin.HistoricResultAdmin;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.admin.HistoricResultAdmin.adapter.AdapterListResult;


public class ResultFragment extends Fragment  implements ResultHistoricContract.ResultView{
    private AdapterListResult adapterListResult;
    private RecyclerView recyclerViewListResult;
    private List<TimeEntryModel> listResult;
    private List<newListResultAdmin> newlistResult;
    private List<TimeEntryModel> list = new ArrayList<>();
    private ResultHistoricContract.ResultPresenter presenter;
    private ProgressBar progressBarResultAdmin;
    View view;

    private int shortAnimTime;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerViewListResult = view.findViewById(R.id.recyclerView_result_admin);
        progressBarResultAdmin=view.findViewById( R.id.progressbar_result_admin );

        presenter = new ResultPresenter(this);
        presenter.getListResult(4,2019);



        //loadList();
        return view;
    }


    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListResult.setLayoutManager(layoutManager);
        recyclerViewListResult.setHasFixedSize(true);

        adapterListResult = new AdapterListResult(newlistResult);
        recyclerViewListResult.setAdapter(adapterListResult);
    }


    private void loadList(){
        list.add(new TimeEntryModel(1,"teste",9,"Banco x",
                "000",9,"111","aaa"));
        list.add(new TimeEntryModel(1,"teste",9,"Banco y",
                "000",9,"111","aaa"));
        list.add(new TimeEntryModel(1,"teste",9,"Santander",
                "000",9,"111","aaa"));
        list.add(new TimeEntryModel(1,"teste",9,"Teste",
                "000",9,"111","aaa"));
        list.add(new TimeEntryModel(1,"teste",9,"Roger",
                "000",9,"111","aaa"));
        listResult = list;
        configAdapter();
    }

    @Override
    public void dialog(boolean Key) {
        recyclerViewListResult.setVisibility(Key ? View.INVISIBLE : View.VISIBLE);
        progressBarResultAdmin.setVisibility(Key ? View.VISIBLE : View.GONE);
        progressBarResultAdmin.animate().setDuration(shortAnimTime).alpha(
                Key ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBarResultAdmin.setVisibility(Key ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showListResult(List<newListResultAdmin> list) {
        newlistResult = list;
        configAdapter();
    }


    @Override
    public void allHours(Long hours) {

    }

    @Override
    public void showErrorMessage(String text) {

    }

    @Override
    public void showSucessMessage(String text) {

    }
}
