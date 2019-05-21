package resource.estagio.workload.data.remote;


import java.util.List;

import resource.estagio.workload.data.remote.model.ActivityModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ActivityAPI {
    @GET("activity/{customerId}")
    Call<List<ActivityModel>> getActivity(@Query("customerId") int id,
                                          @Header("Authorization") String token );
}
