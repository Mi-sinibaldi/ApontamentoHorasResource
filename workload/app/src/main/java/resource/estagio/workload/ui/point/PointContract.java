package resource.estagio.workload.ui.point;

import android.content.Context;

import java.util.Calendar;
import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.CustomerModel;

public class PointContract {

    interface View{
        void notification(String message);

        Context getContext();

        void loadSpinnerCustomer(List<CustomerModel> customerModels);

        void loadSpinnerActivity(List<ActivityModel> activityModels);
    }

    interface Presenter {

        void setPoint(Calendar date, Calendar time, String customer, int customerId,
                      String project, int projectId, String demandNumber, String reason);
        void getCustumers();

        void getActivities(int id);
    }
}
