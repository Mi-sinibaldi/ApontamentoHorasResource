package resource.estagio.workload.ui.point;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import resource.estagio.workload.R;

public class PointFragment extends Fragment {


    // Construtor Vazio
    public PointFragment() {

    }

    public static PointFragment newInstance() {
        PointFragment fragment = new PointFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point, container, false);
    }


}
