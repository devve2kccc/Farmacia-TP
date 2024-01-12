import java.util.ArrayList;

public class Cliente {
    /* variaveis objeto */
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

    /* retorna nome */
    public String getNome() {
        return this.nome;
    }

    /* atribuir nome */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /* retorna nif */
    public int getNif() {
        return this.nif;
    }

    /* atribuir nif */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /* retorna morada */
    public Morada getMorada() {
        return this.morada;
    }

    /* atribuir morada */
    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    /* retorna Historico */
    public ArrayList<Vendas> getHistorico() {
        return this.historico;
    }

    /* adiciona historico */
    public void setHistorico(ArrayList<Vendas> historico) {
        this.historico = historico;
    }

    /* adiciona venda */
    public void addVendaToHistorico(Vendas venda) {
        this.historico.add(venda);
    }

    /* formata dados dos clientes */
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