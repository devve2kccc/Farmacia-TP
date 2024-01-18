import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Vendas {
    /* Formatador de números para a localidade da Alemanha */
    private NumberFormat numberFormat = Utils.getNumberFormat();

    /* Variáveis do objeto */
    private int numVenda;
    private LocalDate data;
    private Cliente cliente;
    private ArrayList<Produto> produto;
    private double total;

    /* Construtor */
    public Vendas(int numVenda, LocalDate data, Cliente cliente, ArrayList<Produto> produto) {
        this.numVenda = numVenda;
        this.data = data;
        this.cliente = cliente;
        this.setProduto(produto);
    }

    /* Retorna o número da venda */
    public int getnumVenda() {
        return this.numVenda;
    }

    /* Atribui o número da venda */
    public void setnumVenda(int numVenda) {
        this.numVenda = numVenda;
    }

    /* Retorna a data */
    public LocalDate getDate() {
        return this.data;
    }

    /* Atribui a data */
    public void setDate(LocalDate date) {
        this.data = date;
    }

    /* Retorna o cliente */
    public Cliente getCliente() {
        return this.cliente;
    }

    /* Atribui o cliente */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /* Retorna a lista de produtos */
    public ArrayList<Produto> getProduto() {
        return this.produto;
    }

    /* Atribui a lista de produtos e calcula o total */
    public void setProduto(ArrayList<Produto> produto) {
        this.produto = produto;
        this.total = 0;
        for (Produto p : this.produto) {
            this.total += p.getPreco();
        }
    }

    /* Retorna o total */
    public double getTotal() {
        return this.total;
    }

    /* Formata os dados da venda */
    @Override
    public String toString() {
        String str = "";

        str += "Venda Nº: " + this.numVenda + "\n";
        str += "Data: " + (this.data == null ? "null" : this.data) + "\n";

        str += "Cliente: " + (this.cliente == null ? "null"
                : "Nome: " + this.cliente.getNome() + ", NIF: " + this.cliente.getNif()) + "\n";

        str += "Produtos: ";
        if (this.produto == null) {
            str += "null";
        } else {
            for (Produto p : this.produto) {
                str += "\n" + (p == null ? "null" : p);
            }
        }
        str += "\n";

        str += "Total: " + numberFormat.format(this.total) + "\n";

        return str;
    }
}