package resource.estagio.workload.ui.home;

import android.content.Context;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

public class HomeContract {

    public interface View{
        void showFragment(Fragment fragment);

        Context Context();

        void exitDialog();
    }

    public interface Presenter{

        void identifyItemClicked(MenuItem menuItem);
    }
}
