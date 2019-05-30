package resource.estagio.workload.ui.admin.HistoricResultAdmin;

public class ResultProject {

    private  String client;
    private String name;
    private int horas;

    public ResultProject(String client, String name, int horas) {
        this.client = client;
        this.name = name;
        this.horas = horas;
    }

    public ResultProject(String aux, int total) {
        this.name = name;
        this.horas = horas;
    }

    public String getClient() {
        return client;
    }

    public String getName() {
        return name;
    }

    public int getHoras() {
        return horas;
    }
}
