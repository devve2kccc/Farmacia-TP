public interface InterfaceFarma {

    /**
     * Insere um produto.
     * 
     * @param p O produto a ser inserido.
     */
    void insereProduto(Produto p);

    /**
     * Insere um cliente.
     * 
     * @param c O cliente a ser inserido.
     */
    void insereCliente(Cliente c);

    /**
     * Insere uma venda.
     * 
     * @param v A venda a ser inserida.
     */
    void insereVenda(Vendas v);

    /**
     * Remove um produto.
     * 
     * @param po O produto a ser removido.
     */
    void removeProduto(Produto po);

    /**
     * Remove um cliente.
     * 
     * @param cl O cliente a ser removido.
     */
    void removeCliente(Cliente cl);
}