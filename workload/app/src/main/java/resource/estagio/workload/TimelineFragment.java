package resource.estagio.workload;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;


public class TimelineFragment extends Fragment {

    AdapterTimeline adapterTimeline;
    View view;
    RecyclerView recyclerViewTimeline;
    List<TimeEntryModel> list = new ArrayList<>();

    public TimelineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline, container, false);
        recyclerViewTimeline = view.findViewById(R.id.id_recyclerview_timeline);
        LoadList();


        return view;
    }

    public void LoadList(){
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));
        list.add(new TimeEntryModel(1,"Apxx",2,"Itau","6666",3,"12/12/2019","jhjjhjhjhj"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTimeline.setLayoutManager(layoutManager);
        recyclerViewTimeline.setHasFixedSize(true);


        adapterTimeline = new AdapterTimeline(list);

        recyclerViewTimeline.setAdapter(adapterTimeline);
    }

}
