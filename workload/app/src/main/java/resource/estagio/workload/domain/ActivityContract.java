package resource.estagio.workload.domain;

import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.ActivityTypeModel;
import resource.estagio.workload.infra.BaseCallback;

public class ActivityContract {

    public interface IRepository {

        void getActivity(int id, String token, BaseCallback<List<ActivityModel>> onResult);

        void getAtcivityType(String token, BaseCallback<List<ActivityTypeModel>> onResult);

        void insertProject(ActivityModel activityModel, String token, BaseCallback<String> onResult);

        void updateProject(ActivityModel activityModel, String token, BaseCallback<String> onResult);

        void deleteProject(long id, String token, BaseCallback<String> onResult);
    }
}
