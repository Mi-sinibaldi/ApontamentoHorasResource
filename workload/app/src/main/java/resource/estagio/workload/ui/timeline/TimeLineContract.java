package resource.estagio.workload.ui.timeline;

import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;

public class TimeLineContract {

    public interface Presenter{
        void getTimeline(int month, int year);

    }
    public interface View{

        void dialog(boolean Key);
        void showListTimeline(List<TimeEntryModel> list);
        void allHours(Long hours);
        void showErrorMessage(String text);
        void showSucessMessage(String text);

    }
}
