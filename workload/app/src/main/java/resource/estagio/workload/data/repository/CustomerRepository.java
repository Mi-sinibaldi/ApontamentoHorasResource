package resource.estagio.workload.data.repository;

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
    public void getCustomers(String token, BaseCallback<List<CustomerModel>> onResult) {
        super.data.restApi(CustomerAPI.class)
                .getCustomer("bearer " + token)
                .enqueue(new Callback<List<CustomerModel>>() {
                    @Override
                    public void onResponse(Call<List<CustomerModel>> call, Response<List<CustomerModel>> response) {
                        if (response.isSuccessful() && response != null)
                            onResult.onSuccessful(response.body());
                        else
                            onResult.onUnsuccessful(response.message());
                    }

                    @Override
                    public void onFailure(Call<List<CustomerModel>> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }

    @Override
    public void deleteCustomer(int id, String token, BaseCallback<Void> onResult) {
        super.data.restApi(CustomerAPI.class)
                .deleteCustomer(id, "bearer " + token)
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
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }

    @Override
    public void postCustomer(Customer customer, String token, BaseCallback<String> onResult) {
        CustomerModel model = new CustomerModel(customer.getId(), customer.getName());
        super.data.restApi(CustomerAPI.class)
                .postCustomer(model, "bearer " + token)
                .enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    onResult.onSuccessful("Cliente cadastrado com sucesso.");
                else
                    onResult.onUnsuccessful("Erro ao cadastrar cliente.");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onResult.onUnsuccessful(t.getMessage());

            }
        });
    }


}
