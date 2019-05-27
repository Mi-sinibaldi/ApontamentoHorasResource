package resource.estagio.workload.ui.admin.project;

import resource.estagio.workload.ui.admin.project.adapterProject.AdapterProject;

public class ProjectContract {

    interface View{

        void listAdapter(AdapterProject adapter);

        void showToast(int message);

        void showError(String error);
    }

    interface Presenter{

        void loadList();
    }
}
