package resource.estagio.workload.data.repository;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.workload.data.remote.CustomerAPI;
import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.domain.Customer;
import resource.estagio.workload.domain.CustomerContract;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository extends Repository implements CustomerContract.IRepository {

    @Override
    public void getCustomers(String token, BaseCallback<List<Customer>> onResult) {
        super.data.restApi(CustomerAPI.class)
                .getCustomer("bearer "+token)
                .enqueue(new Callback<List<CustomerModel>>() {
                    @Override
                    public void onResponse(Call<List<CustomerModel>> call, Response<List<CustomerModel>> response) {
                        List<Customer> lista = new ArrayList<>();
                        if(response.isSuccessful() && response != null) {
                            for (CustomerModel model : response.body()) {
                                lista.add(model.toDomain());
                            }
                            onResult.onSuccessful(lista);
                        }else
                            onResult.onUnsuccessful(response.message());
                    }

                    @Override
                    public void onFailure(Call<List<CustomerModel>> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }

}
