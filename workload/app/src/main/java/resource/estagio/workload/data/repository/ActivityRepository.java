package resource.estagio.workload.data.repository;

import java.util.List;

import resource.estagio.workload.data.remote.ActivityAPI;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.domain.ActivityContract;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRepository extends Repository implements ActivityContract.IRepository {

    @Override
    public void getActivity(int id, String token, BaseCallback<List<ActivityModel>> onResult) {
        super.data.restApi(ActivityAPI.class)
                .getActivity(id,"bearer "+ token)
                .enqueue(new Callback<List<ActivityModel>>() {
                    @Override
                    public void onResponse(Call<List<ActivityModel>> call, Response<List<ActivityModel>> response) {
                        if(response.isSuccessful() && response.body() != null){
                            onResult.onSuccessful(response.body());
                        }else{
                            onResult.onUnsuccessful(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ActivityModel>> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }
}
