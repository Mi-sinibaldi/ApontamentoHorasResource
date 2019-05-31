package resource.estagio.workload.ui.admin.project;

import android.content.Context;

import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;

public class ProjectContract {

    interface View {

        void showToast(int message);

        void showError(int error);

        void showError(String error);

        void showProgress(boolean result);

        void showVisibility();

        void showInvisibility();

        void listAdapter(List<ActivityModel> value, boolean status);

        void reloadList();

        Context getContext();
    }

    interface Presenter {

        void loadList(int idCustomer, boolean status);

        void deleteCustomer(ActivityModel model);
    }
}
