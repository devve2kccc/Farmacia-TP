import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Farmacia que implementa InterfaceFarma.
 * Gerencia produtos, clientes e vendas.
 */
public class Farmacia implements InterfaceFarma {
    private static NumberFormat numberFormat = Utils.getNumberFormat();

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

    public int getClientByName(Scanner scan) {
        System.out.print("Nome do Cliente: ");
        String name = scan.nextLine();
        ArrayList<Cliente> matchingClients = new ArrayList<>();
        System.out.println();
        for (Cliente cliente : this.getClientes()) {
            if (cliente.getNome().toLowerCase().contains(name.toLowerCase())) {
                matchingClients.add(cliente);
            }
        }
        if (matchingClients.isEmpty()) {
            System.out.println("Não existem clientes com esse nome: " + name);
            return -1;
        } else {
            for (int i = 0; i < matchingClients.size(); i++) {
                System.out.println(
                        "   " + (i + 1) + " - " + matchingClients.get(i).getNome() + " - "
                                + matchingClients.get(i).getNif());
            }
            System.out.println();

            int clientIndex = Main.getMenuChoiceWithIndex(scan, 1, matchingClients.size());

            Cliente selectedClient = matchingClients.get(clientIndex);
            System.out.println();
            System.out.println("Selecionou: " + "\n" + selectedClient);
            return clientIndex;
        }
    }

    public int getClientByNIF(Scanner scan) {
        System.out.print("NIF do Cliente: ");
        int NIF = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < this.clientes.size(); i++) {
            if (this.clientes.get(i).getNif() == NIF) {
                System.out.println(this.clientes.get(i));
                return i;
            }
        }
        return -1;
    }

    public void mostrarCategorias() {
        int categoriaIndex = 1;
        for (Categoria categoria : Categoria.values()) {
            System.out.println(categoriaIndex + " - " + categoria.getDescricao());
            categoriaIndex++;
        }
        System.out.println();
    }

    public void listarProdutos() {
        int listaProdutosIndex = 1;
        for (Produto listProduto : this.getProdutos()) {
            System.out.println("    " + listaProdutosIndex + " - " + listProduto.getNome());
            listaProdutosIndex++;
        }
    }

    public void listarProdutosIndisponiveis() {
        int listaProdutosIndex = 1;
        for (Produto listProduto : this.getProdutosIndisponiveis()) {
            System.out.println("    " + listaProdutosIndex + " - " + listProduto.getNome());
            listaProdutosIndex++;
        }
    }

    public ArrayList<Produto> getProductosPorCategoria(Categoria categoria) {
        ArrayList<Produto> produtosCategoria = new ArrayList<>();
        for (Produto produto : this.getProdutos()) {
            if (produto.getCategoria() == categoria) {
                produtosCategoria.add(produto);
            }
        }
        return produtosCategoria;
    }

    public void updateStock(ArrayList<Produto> produtos) {
        for (Produto pEscolhido : produtos) {
            for (Produto pRegistado : this.getProdutos()) {
                if (pEscolhido.equals(pRegistado)) {
                    pRegistado.setStock(pRegistado.getStock() - 1);
                    if (pRegistado.getStock() < 1) {
                        this.removeProduto(pRegistado);
                    }
                    break;
                }
            }
        }
    }

    public void listarClientes() {
        int listaClientesIndex = 1;
        for (Cliente listaClientes : this.getClientes()) {
            System.out.println("    " + listaClientesIndex + " - " + listaClientes.getNome() + " | "
                    + listaClientes.getNif());
            listaClientesIndex++;
        }
    }

    public void listarClientesIndisponiveis() {
        int listaClientesIndex = 1;
        for (Cliente listaClientes : this.getClientesIndisponiveis()) {
            System.out.println("    " + listaClientesIndex + " - " + listaClientes.getNome() + " | "
                    + listaClientes.getNif());
            listaClientesIndex++;
        }
    }

    public void mostrarInformacaoCliente(int escolhaCliente) {
        System.out.println("Informação do cliente: ");
        Cliente clienteInfo = this.getClientes().get(escolhaCliente);
        System.out.println(clienteInfo);
    }

    public void listaVendas() {
        System.out.println(" \n Lista de Vendas ");
        for (Vendas v : this.getVendas()) {
            System.out.println(v);
        }
    }

    public void comprasTotais() {
        System.out.println(" \n Numero total de vendas: ");
        double totalVenda = 0;
        for (Vendas totalVendas : this.getVendas()) {
            totalVenda += totalVendas.getTotal();
        }

        int totalNvendas = this.getVendas().size();

        System.out.println("    " + "Nº Total de vendas: " + totalNvendas + " | " + "Faturamento: "
                + numberFormat.format(totalVenda));
    }

    public void imprimirRecibo(int clientIndex, int ultimaVenda) {
        System.out.println("-------- Compra Finalizada! --------");
        System.out.println("NIF: " + this.getClientes().get(clientIndex).getNif());
        System.out.println("Produtos comprados:");
        for (Produto produtoVendido : this.getVendas().get(ultimaVenda).getProduto()) {
            System.out.println(produtoVendido.getNome());
        }
        System.out.println("Total: " + numberFormat.format(this.getVendas().get(ultimaVenda).getTotal()));
    }

}