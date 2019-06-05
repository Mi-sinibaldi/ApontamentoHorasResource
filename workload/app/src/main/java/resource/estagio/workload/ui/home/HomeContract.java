package resource.estagio.workload.ui.home;

import android.view.MenuItem;

import androidx.fragment.app.Fragment;

public class HomeContract {

    public interface View {

        void showFragment(Fragment fragment);

        void dialog(boolean key);

        void showDialogChooser();
    }

    public interface Presenter {

        void identifyItemClicked(MenuItem menuItem);
    }
}
