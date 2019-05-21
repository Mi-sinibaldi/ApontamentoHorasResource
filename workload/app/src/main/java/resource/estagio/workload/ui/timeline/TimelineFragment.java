package resource.estagio.workload.ui.timeline;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import resource.estagio.workload.ui.timeline.adapterTimeLine.AdapterTimeline;
import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.model.TimeEntryModel;

public class TimelineFragment extends Fragment implements TimeLineContract.View {

    private AdapterTimeline adapterTimeline;
    private View view;
    private RecyclerView recyclerViewTimeline;
    private List<TimeEntryModel> list = new ArrayList<>();
    private List<TimeEntryModel> listWorkLoad;
    private TextView textViewMonth;
    private TextView textViewYear;
    private ImageView imageViewMonthLeft;
    private ImageView imageViewMonthRight;
    private Calendar calendar;
    private TimeLineContract.Presenter presenter;
    private ProgressDialog pd;
    private int month;
    private int year;

    public TimelineFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline, container, false);
        presenter = new TimeLinePresenter(this);
        calendar = Calendar.getInstance();

        loadUI();
        setTextDate();
        final int thisMonth = calendar.get(Calendar.MONTH);
        //LoadList();

        imageViewMonthLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( month >= thisMonth-2) {
                    calendar.add(Calendar.MONTH, -1);
                    setTextDate();
                    imageViewMonthLeft.setVisibility(View.VISIBLE);
                    imageViewMonthRight.setVisibility(View.VISIBLE);
                }else{
                    imageViewMonthLeft.setVisibility(View.INVISIBLE);
                }
                if( month == thisMonth-2) {
                    imageViewMonthLeft.setVisibility(View.INVISIBLE);
                }
            }
        });


        imageViewMonthRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( month <= thisMonth) {
                    calendar.add(Calendar.MONTH, +1);
                    setTextDate();
                    imageViewMonthRight.setVisibility(View.VISIBLE);
                    imageViewMonthLeft.setVisibility(View.VISIBLE);
                }else{
                    imageViewMonthRight.setVisibility(View.INVISIBLE);
                }
                if( month == thisMonth+1) {
                    imageViewMonthRight.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;
    }

    private void setTextDate() {

        textViewMonth.setText(new SimpleDateFormat("MMM").format(calendar.getTime()));
        textViewYear.setText("" + calendar.get(Calendar.YEAR));

        month = calendar.get(Calendar.MONTH)+1;
        year = calendar.get(Calendar.YEAR);

        presenter.getTimeline(month, year);
    }

    private void loadUI() {
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(false);
        recyclerViewTimeline = view.findViewById(R.id.id_recyclerview_timeline);
        textViewMonth = view.findViewById(R.id.text_view_month);
        textViewYear = view.findViewById(R.id.text_view_year);
        imageViewMonthLeft = view.findViewById(R.id.image_view_month_left);
        imageViewMonthRight = view.findViewById(R.id.image_view_month_right);
    }


    public void LoadList() {

        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "05/01/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "06/02/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "07/03/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "08/04/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "09/05/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "10/06/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "11/07/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "12/08/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "13/09/2019", "jhjjhjhjhj"));
        list.add(new TimeEntryModel(1, "Apxx", 2, "Itau", "6666", 3, "14/10/2019", "jhjjhjhjhj"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTimeline.setLayoutManager(layoutManager);
        recyclerViewTimeline.setHasFixedSize(true);

        adapterTimeline = new AdapterTimeline(list);
        recyclerViewTimeline.setAdapter(adapterTimeline);
    }

    @Override
    public void dialog(boolean Key) {
        pd.setMessage("Carregando...");
        if(Key){
            pd.show();
        }else{
            pd.dismiss();
        }
    }

    @Override
    public void showListTimeline(List<TimeEntryModel> list) {
        listWorkLoad = list;
        configAdapter();
    }

    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTimeline.setLayoutManager(layoutManager);
        recyclerViewTimeline.setHasFixedSize(true);

        adapterTimeline = new AdapterTimeline(listWorkLoad);
        recyclerViewTimeline.setAdapter(adapterTimeline);
    }

    @Override
    public void allHours(Long hours) {

    }

    @Override
    public void showErrorMessage(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSucessMessage(String text) {

    }

}
