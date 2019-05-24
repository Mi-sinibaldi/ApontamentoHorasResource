package resource.estagio.workload.ui.admin.HistoricResultAdmin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import resource.estagio.workload.R;


public class HistoricFragment extends Fragment {


    public HistoricFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historic, container, false);
    }

}
