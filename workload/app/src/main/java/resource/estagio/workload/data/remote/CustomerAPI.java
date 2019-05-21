package resource.estagio.workload.data.remote;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CustomerAPI {

    @GET("customer")
    Call<List<CustomerModel>> getCustomer(@Header("Authotization") String token);

}
