import java.util.ArrayList;

/* Definir uma classe  */
public class Farmacia implements InterfaceFarma {

    ArrayList<Produto> produtos;
    ArrayList<Produto> produtosIndisponiveis;
    ArrayList<Cliente> clientes;
    ArrayList<Vendas> vendas;

    /* */
    public Farmacia() {
        this.produtos = new ArrayList<Produto>();
        this.produtosIndisponiveis = new ArrayList<Produto>();
        this.clientes = new ArrayList<Cliente>();
        this.vendas = new ArrayList<Vendas>();
    }

    @Override
    public void insereProduto(Produto p) {
        this.produtos.add(p);
    }

    @Override
    public void insereCliente(Cliente c) {
        this.clientes.add(c);
    }

    @Override
    public void insereVenda(Vendas v) {
        this.vendas.add(v);
    }

    @Override
    public void removeProduto(Produto po) {
        this.produtosIndisponiveis.add(po);
        this.produtos.remove(po);
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public ArrayList<Produto> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Vendas> getVendas() {
        return this.vendas;
    }

    public ArrayList<Produto> getProdutosIndisponiveis() {
        return this.produtosIndisponiveis;
    }

}
