package resource.estagio.workload.ui.admin;

import android.view.MenuItem;

import androidx.fragment.app.Fragment;

public class HomeAdminContract {

    interface Presenter{

        void identifyItemClicked(MenuItem menuItem);
    }

    interface View{

        void showFragment(Fragment fragment);

        void showDialogChooser();

        void enableNavigation(boolean key);
    }
}
