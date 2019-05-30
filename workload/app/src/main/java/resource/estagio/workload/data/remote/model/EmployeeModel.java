package resource.estagio.workload.data.remote.model;

public class EmployeeModel {

    private String nome;
    private String re;

    public EmployeeModel() {
    }

    public EmployeeModel(String nome, String re) {
        this.nome = nome;
        this.re = re;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }
}
