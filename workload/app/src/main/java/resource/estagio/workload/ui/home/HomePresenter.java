package resource.estagio.workload.ui.home;

import android.view.MenuItem;

import resource.estagio.workload.R;
import resource.estagio.workload.ui.point.PointFragment;
import resource.estagio.workload.ui.timeline.TimelineFragment;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void identifyItemClicked(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.ic_pointing:
                view.showFragment(new PointFragment(view.Context()));
                break;
            case R.id.ic_history:
                view.showFragment(new TimelineFragment());
                break;
            case R.id.ic_config:
                break;
            case R.id.ic_exit:
                break;
        }
    }

}
