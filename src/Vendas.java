import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Vendas {
    NumberFormat numberFormat = Utils.getNumberFormat();

    private int numVenda;
    private LocalDate data;
    private Cliente cliente;
    private ArrayList<Produto> produto;
    private double total;

    public Vendas(int numVenda, LocalDate data, Cliente cliente, ArrayList<Produto> produto) {
        this.numVenda = numVenda;
        this.data = data;
        this.cliente = cliente;
        this.setProduto(produto);
    }

    public int getnumVenda() {
        return this.numVenda;
    }

    public void setnumVenda(int numVenda) {
        this.numVenda = numVenda;
    }

    public LocalDate getDate() {
        return this.data;
    }

    public void setDate(LocalDate date) {
        this.data = date;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Produto> getProduto() {
        return this.produto;
    }

    public void setProduto(ArrayList<Produto> produto) {
        this.produto = produto;
        this.total = 0;
        for (Produto p : this.produto) {
            this.total += p.getPreco();
        }
    }

    public double getTotal() {
        return this.total;
    }

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

        str += "Total: " + this.total + "\n";

        return str;
    }
}
