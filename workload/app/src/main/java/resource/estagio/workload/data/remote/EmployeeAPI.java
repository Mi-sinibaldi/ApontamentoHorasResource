package resource.estagio.workload.data.remote;

import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmployeeAPI {
    @GET("employee/entries")
    Call<List<TimeEntryModel>> getWorkList(
            @Query("month") int month,
            @Query("year") int year,
            @Header("Authorization") String token);

    @POST("employee/entry")
    Call<Void> postEntry(
            @Body TimeEntryModel timeEntryModel,
            @Header("Authorization") String token);
}
