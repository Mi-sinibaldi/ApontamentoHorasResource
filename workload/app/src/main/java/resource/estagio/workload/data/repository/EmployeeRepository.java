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
        super.data.restApi(EmployeeAPI.class).getWorkList(month, year, "bearer DrdHZtH-ilLpZSpQVeKRRSTdn7AZCjW-ySgdmkwIUKOh4hvy1w65FQnVLN6mBG5JqTk96Gsf0ID3p3alRf4X67pn5fC3aMYJ8IBTnRFzk3EDsLaIbT2WlcIGFNl1xswOlWAxB5pDSG2EDZ_TDyaGBVxeNGWilCooUdX5nljBmZMYLeOETCIJmFsn-GmS5PXAutGEOFkChPE4XYQBFF1Vk_D00Hjc7DwLpQ2PRl91wZrJo-D-cjJQVCoVDTl3SZAa4tmQQgf3PFBj1j75y-tpVA")
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

    @Override
    public void postEntry(TimeEntryModel timeEntryModel, String token, BaseCallback<Void> onResult) {
        super.data.restApi(EmployeeAPI.class)
                .postEntry(timeEntryModel, "bearer " + token)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful())
                            onResult.onSuccessful(response.body());
                        else
                            onResult.onUnsuccessful(response.message());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }


}
