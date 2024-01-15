import java.util.ArrayList;

public class Cliente {
    /* Variáveis do objeto */
    private String nome;
    private int nif;
    private Morada morada;
    private ArrayList<Vendas> historico = new ArrayList<>();

    /* Construtor */
    public Cliente(String nome, int nif, Morada morada, ArrayList<Vendas> historico) {
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.historico = historico;
    }

    /* Retorna o nome */
    public String getNome() {
        return this.nome;
    }

    /* Atribui o nome */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /* Retorna o NIF */
    public int getNif() {
        return this.nif;
    }

    /* Atribui o NIF */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /* Retorna a morada */
    public Morada getMorada() {
        return this.morada;
    }

    /* Atribui a morada */
    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    /* Retorna o histórico */
    public ArrayList<Vendas> getHistorico() {
        return this.historico;
    }

    /* Atribui o histórico */
    public void setHistorico(ArrayList<Vendas> historico) {
        this.historico = historico;
    }

    /* Adiciona uma venda ao histórico */
    public void addVendaToHistorico(Vendas venda) {
        this.historico.add(venda);
    }

    /* Formata os dados dos clientes */
    @Override
    public String toString() {
        String fichaCliente = "";

        fichaCliente += "Nome: " + this.nome + "\n";
        fichaCliente += "NIF: " + this.nif + "\n";
        fichaCliente += "Morada: " + (this.morada == null ? "null" : this.morada) + "\n";

        fichaCliente += "Historico: ";
        if (this.historico == null) {
            fichaCliente += "null";
        } else {
            for (Vendas v : this.historico) {
                fichaCliente += "\nVenda Nº: " + v.getnumVenda() + ", Data: "
                        + (v.getDate() == null ? "null" : v.getDate());
            }
        }
        fichaCliente += "\n";

        return fichaCliente;
    }

}