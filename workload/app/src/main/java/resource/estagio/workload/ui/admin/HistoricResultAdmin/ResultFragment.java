package resource.estagio.workload.ui.admin.HistoricResultAdmin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.ui.admin.HistoricResultAdmin.adapter.AdapterListResult;


public class ResultFragment extends Fragment {
    private AdapterListResult adapterListResult;
    private RecyclerView recyclerViewListResult;
    private List<TimeEntryModel> listResult;
    private List<TimeEntryModel> list = new ArrayList<>();
    View view;
    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);


        recyclerViewListResult = view.findViewById(R.id.recyclerView_result_admin);
        loadList();
        return view;
    }


    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListResult.setLayoutManager(layoutManager);
        recyclerViewListResult.setHasFixedSize(true);

        adapterListResult = new AdapterListResult(listResult);
        recyclerViewListResult.setAdapter(adapterListResult);
    }


    private void loadList(){
        list.add(new TimeEntryModel(1,"teste",9,"",
                "000",9,"111","aaa"));
        listResult = list;
        configAdapter();
    }
}
