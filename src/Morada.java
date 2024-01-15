public class Morada {

    /* Variáveis do objeto */
    private String local;
    private String localidade;

    /* Construtor */
    public Morada(String local, String localidade) {
        this.local = local;
        this.localidade = localidade;
    }

    /* Retorna o local */
    public String getLocal() {
        return local;
    }

    /* Atribui o local */
    public void setLocal(String local) {
        this.local = local;
    }

    /* Retorna a localidade */
    public String getLocalidade() {
        return localidade;
    }

    /* Atribui a localidade */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /* Formata os dados da morada */
    @Override
    public String toString() {
        return local + ", " + localidade;
    }

}