import java.util.ArrayList;

/**
 * Classe Farmacia que implementa InterfaceFarma.
 * Gerencia produtos, clientes e vendas.
 */
public class Farmacia implements InterfaceFarma {

    // Lista de produtos disponíveis
    ArrayList<Produto> produtos;
    // Lista de produtos indisponíveis
    ArrayList<Produto> produtosIndisponiveis;
    // Lista de clientes ativos
    ArrayList<Cliente> clientes;
    // Lista de clientes inativos
    ArrayList<Cliente> clientesIndisponiveis;
    // Lista de vendas
    ArrayList<Vendas> vendas;

    /**
     * Construtor para a classe Farmacia.
     * Inicializa todas as ArrayLists.
     */
    public Farmacia() {
        this.produtos = new ArrayList<Produto>();
        this.produtosIndisponiveis = new ArrayList<Produto>();
        this.clientes = new ArrayList<Cliente>();
        this.clientesIndisponiveis = new ArrayList<Cliente>();
        this.vendas = new ArrayList<Vendas>();
    }

    /**
     * Insere um produto na lista de produtos.
     * 
     * @param p O produto a ser inserido.
     */
    @Override
    public void insereProduto(Produto p) {
        this.produtos.add(p);
    }

    /**
     * Insere um cliente na lista de clientes.
     * 
     * @param c O cliente a ser inserido.
     */
    @Override
    public void insereCliente(Cliente c) {
        this.clientes.add(c);
    }

    /**
     * Insere uma venda na lista de vendas.
     * 
     * @param v A venda a ser inserida.
     */
    @Override
    public void insereVenda(Vendas v) {
        this.vendas.add(v);
    }

    /**
     * Remove um produto da lista de produtos e o adiciona à lista de produtos
     * indisponíveis.
     * 
     * @param po O produto a ser removido.
     */
    @Override
    public void removeProduto(Produto po) {
        this.produtosIndisponiveis.add(po);
        this.produtos.remove(po);
    }

    /**
     * Remove um cliente da lista de clientes e o adiciona à lista de clientes
     * inativos.
     * 
     * @param cl O cliente a ser removido.
     */
    @Override
    public void removeCliente(Cliente cl) {
        this.clientesIndisponiveis.add(cl);
        this.clientes.remove(cl);
    }

    /**
     * Retorna a lista de clientes.
     * 
     * @return A lista de clientes.
     */
    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    /**
     * Retorna a lista de produtos.
     * 
     * @return A lista de produtos.
     */
    public ArrayList<Produto> getProdutos() {
        return this.produtos;
    }

    /**
     * Define a lista de produtos.
     * 
     * @param produtos A nova lista de produtos.
     */
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Retorna a lista de vendas.
     * 
     * @return A lista de vendas.
     */
    public ArrayList<Vendas> getVendas() {
        return this.vendas;
    }

    /**
     * Retorna a lista de produtos indisponíveis.
     * 
     * @return A lista de produtos indisponíveis.
     */
    public ArrayList<Produto> getProdutosIndisponiveis() {
        return this.produtosIndisponiveis;
    }

    /**
     * Retorna a lista de clientes inativos.
     * 
     * @return A lista de clientes inativos.
     */
    public ArrayList<Cliente> getClientesIndisponiveis() {
        return this.clientesIndisponiveis;
    }

}