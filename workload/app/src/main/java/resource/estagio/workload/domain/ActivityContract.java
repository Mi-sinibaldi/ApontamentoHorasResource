package resource.estagio.workload.domain;

import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.infra.BaseCallback;

public class ActivityContract {

    public interface IRepository{

        void getActivity(int id, String token, BaseCallback<List<ActivityModel>> onResult);

    }
}
