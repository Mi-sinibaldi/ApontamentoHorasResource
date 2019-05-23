package resource.estagio.workload.ui.timeline;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import resource.estagio.workload.ui.home.HomeContract;
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
    private int month;
    private int year;
    private ProgressBar progressBarTimeLine;
    TextView text_dialog_error;

    private Button buttonConfirm, buttonChooser, buttonChosserYes,
            buttonChosserNo, buttonConfirmCheck, buttonError;

    private Dialog dialog;
    private HomeContract.View viewHome;
    public TimelineFragment(HomeContract.View view) {
        this.viewHome = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_timeline, container, false);
        loadUI();
        calendar = Calendar.getInstance();
        setTextDate();
        final int thisMonth = calendar.get(Calendar.MONTH);

        imageViewMonthLeft.setOnClickListener(v -> {

            if (month >= thisMonth - 2) {
                calendar.add(Calendar.MONTH, -1);
                setTextDate();
                imageViewMonthLeft.setVisibility(View.VISIBLE);
                imageViewMonthRight.setVisibility(View.VISIBLE);
            } else {
                imageViewMonthLeft.setVisibility(View.INVISIBLE);
            }
            if (month == thisMonth - 6) {
                imageViewMonthLeft.setVisibility(View.INVISIBLE);
            }
        });


        imageViewMonthRight.setOnClickListener(v -> {

            if (month <= thisMonth) {
                calendar.add(Calendar.MONTH, +1);
                setTextDate();
                imageViewMonthRight.setVisibility(View.VISIBLE);
                imageViewMonthLeft.setVisibility(View.VISIBLE);
            } else {
                imageViewMonthRight.setVisibility(View.INVISIBLE);
            }
            if (month == thisMonth + 1) {
                imageViewMonthRight.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    private void setTextDate() {

        textViewMonth.setText(new SimpleDateFormat("MMM").format(calendar.getTime()));
        textViewYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));

        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);

        presenter.getTimeline(month, year);
    }

    private void loadUI() {

        presenter = new TimeLinePresenter(this);
        buttonConfirm = view.findViewById(R.id.button_point_confirm);

        recyclerViewTimeline = view.findViewById(R.id.id_recyclerview_timeline);
        textViewMonth = view.findViewById(R.id.text_view_month_timeline);
        textViewYear = view.findViewById(R.id.text_view_year_timeline);
        imageViewMonthLeft = view.findViewById(R.id.image_view_month_left_timeline);
        imageViewMonthRight = view.findViewById(R.id.image_view_month_right_timeline);
        progressBarTimeLine = view.findViewById(R.id.progressBarTimeLine);
        imageViewMonthRight.setVisibility(View.INVISIBLE);
    }


    @Override
    public void dialog(boolean Key) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        recyclerViewTimeline.setVisibility(Key ? View.INVISIBLE : View.VISIBLE);
        progressBarTimeLine.setVisibility(Key ? View.VISIBLE : View.GONE);
        progressBarTimeLine.animate().setDuration(shortAnimTime).alpha(
                Key ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBarTimeLine.setVisibility(Key ? View.VISIBLE : View.GONE);
            }
        });
        viewHome.dialog(Key);
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

    public void showDialogError(String text) {
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
        buttonError.setOnClickListener(v -> dialog.dismiss());
    }

    @Override
    public void allHours(Long hours) {

    }

    @Override
    public void showErrorMessage(String text) {
        showDialogError(text);
    }

    @Override
    public void showSucessMessage(String text) {

    }

}
