package resource.estagio.workload.ui.admin.HistoricResultAdmin;

import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;

public class ResultHistoricContract {

    public interface historicPresenter{
        void getTimeline(int month, int year);

    }
    public interface historicView{

        void dialog(boolean Key);
        void showListTimeline(List<TimeEntryModel> list);
        void allHours(Long hours);
        void showErrorMessage(String text);
        void showSucessMessage(String text);

    }


    public interface ResultPresenter{
        void getListResult(int month, int year);

    }
    public interface ResultView{

        void dialog(boolean Key);
        void showListResult(List<newListResultAdmin> list);
        void allHours(Long hours);
        void showErrorMessage(String text);
        void showSucessMessage(String text);

    }
}
