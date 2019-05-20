package resource.estagio.workload.domain;

import java.util.List;

import resource.estagio.workload.data.remote.model.TimeEntryModel;
import resource.estagio.workload.infra.BaseCallback;

public class EntryContract {

    public interface IRepository {
        void getEntries(BaseCallback<List<Entry>> onResult, String token);

        void postEntry(TimeEntryModel entry, String token, BaseCallback<Void> onResult);
    }
}
