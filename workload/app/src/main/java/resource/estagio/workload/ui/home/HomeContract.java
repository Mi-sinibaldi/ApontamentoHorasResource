package resource.estagio.workload.ui.home;

import android.app.Activity;

import resource.estagio.workload.ui.HomeDefault;

public class HomeContract {

    public interface View extends HomeDefault.View {

        Activity getActivity();
    }

    public interface Presenter extends HomeDefault.Presenter {
    }
}
