package resource.estagio.workload.data.repository;

import java.util.List;

import resource.estagio.workload.R;
import resource.estagio.workload.data.remote.ActivityAPI;
import resource.estagio.workload.data.remote.model.ActivityModel;
import resource.estagio.workload.data.remote.model.ActivityTypeModel;
import resource.estagio.workload.domain.Project;
import resource.estagio.workload.domain.ProjectContract;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRepository extends Repository implements ProjectContract.IRepository {

    @Override
    public void getActivity(int id, String token, BaseCallback<List<ActivityModel>> onResult) {
        super.data.restApi(ActivityAPI.class)
                .getActivity(id, "bearer " + token)
                .enqueue(new Callback<List<ActivityModel>>() {
                    @Override
                    public void onResponse(Call<List<ActivityModel>>
                                                   call, Response<List<ActivityModel>> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            onResult.onSuccessful(response.body());
                        } else {
                            onResult.onUnsuccessful(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ActivityModel>> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });

    }

    @Override
    public void getActivityType(String token, BaseCallback<List<ActivityTypeModel>> onResult) {
        super.data.restApi(ActivityAPI.class)
                .getActivityType("bearer "+token)
                .enqueue(new Callback<List<ActivityTypeModel>>() {
                    @Override
                    public void onResponse(Call<List<ActivityTypeModel>> call, Response<List<ActivityTypeModel>> response) {
                        if(response.isSuccessful()&&  response.body() != null ){
                            onResult.onSuccessful(response.body());
                        }
                        else{
                            onResult.onUnsuccessful(response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ActivityTypeModel>> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }

    @Override
    public void insertProject(Project project, String token, BaseCallback<String> onResult) {
        ActivityModel activityModel = new ActivityModel(project.getId(), project.getNameProject(), project.getDemandNumber(),
                project.getActivityTypeModel().getId(), project.getCustomer().getId(),
                project.getCustomer().getName());
        super.data.restApi(ActivityAPI.class)
                .insertProject(activityModel, "bearer "+token)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            onResult.onSuccessful("Projeto inserido com sucesso.");
                        }
                        else{
                            onResult.onUnsuccessful("Erro ao cadastrar o projeto .");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                            onResult.onUnsuccessful(t.getMessage());
                    }
                });

    }

    @Override
    public void updateProject(ActivityModel activityModel, String token, BaseCallback<String> onResult) {
        super.data.restApi(ActivityAPI.class)
                .updateProject(activityModel, "bearer "+token)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            onResult.onSuccessful("Projeto atualizado com sucesso.");
                        }
                        else{
                            onResult.onUnsuccessful("Erro ao atualizar o projeto.");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                            onResult.onUnsuccessful(t.getMessage());
                    }
                });

    }

    @Override
    public void deleteProject(long id, String token, BaseCallback<String> onResult) {
        super.data.restApi(ActivityAPI.class)
                .deleteProject(id, "bearer " + token)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            onResult.onSuccessful(String.valueOf(R.string.project_deleted_successfully));
                        } else {
                            onResult.onUnsuccessful(String.valueOf(R.string.project_not_deleted));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());

                    }
                });

    }
}

