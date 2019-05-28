package resource.estagio.workload.ui.admin.HistoricResultAdmin;

public class ResultProject {

    private String name;
    private int horas;

    public ResultProject(String name, int horas) {
        this.name = name;
        this.horas = horas;
    }

    public String getName() {
        return name;
    }

    public int getHoras() {
        return horas;
    }
}
