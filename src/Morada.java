
public class Morada {

    private String local;
    private String localidade;

    public Morada(String local, String localidade) {
        this.local = local;
        this.localidade = localidade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public String toString() {
        return local + ", " + localidade;
    }

}
