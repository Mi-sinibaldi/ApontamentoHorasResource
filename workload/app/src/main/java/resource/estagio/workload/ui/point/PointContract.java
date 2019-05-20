package resource.estagio.workload.ui.point;

import android.content.Context;

import java.util.Calendar;

public class PointContract {

    interface View{
        void notification(String message);

        Context getContext();
    }

    interface Presenter {

        void setPoint(Calendar date, Calendar time, String customer, int customerId,
                      String project, int projectId, String demandNumber, String reason);
        void getCustumers();

        void getActivities(long id);
    }
}
