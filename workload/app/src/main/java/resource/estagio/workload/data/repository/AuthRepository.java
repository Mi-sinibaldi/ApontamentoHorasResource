package resource.estagio.workload.data.repository;

import resource.estagio.workload.data.remote.model.LoginModel;
import resource.estagio.workload.domain.User;
import resource.estagio.workload.domain.UserContract;
import resource.estagio.workload.infra.BaseCallback;
import resource.estagio.workload.infra.Repository;
import resource.estagio.workload.infra.mock.FakeApiInterface;
import resource.estagio.workload.infra.mock.FakeRestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository extends Repository implements UserContract.IRepository {
    @Override
    public void login(String username, String password, BaseCallback<User> onResult) {
        FakeRestClient.getApiInterface()
                .login("password", username, password)
                .enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        if (response.isSuccessful() && response.body() != null)
                            onResult.onSuccessful(response.body().toDomain());
                        else if (response.code() == 400)
                            onResult.onUnsuccessful("Usuário ou senha inválido!");
                        else
                            onResult.onUnsuccessful(response.message());
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        onResult.onUnsuccessful("Verifique sua conexão com a internet");
                    }
                });
    }

//    @Override
//    public void login(String username, String password, BaseCallback<User> onResult) {
//        super.data.restApi(AuthAPI.class)
//                .login("password", username, password)
//                .enqueue(new Callback<LoginModel>() {
//                    @Override
//                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
//                        if (response.isSuccessful() && response.body() != null)
//                            onResult.onSuccessful(response.body().toDomain());
//                        else
//                            onResult.onUnsuccessful(response.message());
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginModel> call, Throwable t) {
//                        onResult.onUnsuccessful(t.getMessage());
//                    }
//                });
//    }
}
