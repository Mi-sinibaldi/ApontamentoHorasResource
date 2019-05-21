package resource.estagio.workload.data.repository;

import java.util.List;

import resource.estagio.workload.data.remote.EmployeeAPI;
import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.domain.employee.EmployeeContract;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository extends Repository implements EmployeeContract.IRepository {


    @Override
    public void getWorkList(int month, int year, BaseCallback<List<TimeEntryModel>> onResult) {
        super.data.restApi(EmployeeAPI.class).getWorkList(month, year, "token")
                .enqueue(new Callback<List<TimeEntryModel>>() {

            @Override
            public void onResponse(Call<List<TimeEntryModel>>
                                           call, Response<List<TimeEntryModel>> response) {

                if (!response.isSuccessful()) {
                    onResult.onUnsuccessful("Erro\n" + response.message());
                    return;
                }
                onResult.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<List<TimeEntryModel>> call, Throwable t) {
                onResult.onUnsuccessful(t.getMessage());
            }
        });
    }
}
