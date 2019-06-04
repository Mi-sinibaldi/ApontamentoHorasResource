package resource.estagio.workload.data.repository;

import java.util.List;

import resource.estagio.workload.ConstantApp;
import resource.estagio.workload.data.remote.EmployeeAPI;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.domain.employee.EmployeeContract;
import resource.estagio.workload.infra.App;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository extends Repository implements EmployeeContract.IRepository {

    public static final int UNAUTHORIDEZ = 401;

    @Override
    public void getWorkList(int month, int year, BaseCallback<List<TimeEntryModel>> onResult) {
        super.data.restApi(EmployeeAPI.class).getWorkList(month, year, ConstantApp.BEARER + App.getUser().getAccessToken())
                .enqueue(new Callback<List<TimeEntryModel>>() {
                    @Override
                    public void onResponse(Call<List<TimeEntryModel>>
                                                   call, Response<List<TimeEntryModel>> response) {
                        if (!response.isSuccessful()) {
                            onResult.onUnsuccessful(ConstantApp.UNLOADED_LIST);
                            return;
                        }

                        if (response.code() == UNAUTHORIDEZ) {
                            onResult.onUnsuccessful(ConstantApp.UNAUTHORIDED_USER);
                            return;
                        }
                        onResult.onSuccessful(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<TimeEntryModel>> call, Throwable t) {
                        onResult.onUnsuccessful(ConstantApp.CONNECTION_INTERNET);
                    }
                });
    }


    @Override
    public void postEntry(TimeEntryModel timeEntryModel, String token, BaseCallback<Void> onResult) {
        super.data.restApi(EmployeeAPI.class)
                .postEntry(timeEntryModel, ConstantApp.BEARER + token)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful())
                            onResult.onSuccessful(response.body());
                        else
                            onResult.onUnsuccessful(response.message());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        onResult.onUnsuccessful(ConstantApp.CONNECTION_INTERNET);
                    }
                });
    }


}
