package resource.estagio.workload.domain;

import java.util.List;

import resource.estagio.workload.data.remote.model.CustomerModel;
import resource.estagio.workload.infra.BaseCallback;

public class CustomerContract {

    public interface IRepository {
        void getCustomers(String token, BaseCallback<List<CustomerModel>> onResult);
    }


}
