import java.text.NumberFormat;
import java.time.LocalDate;

public class Produto {
    NumberFormat numberFormat = Utils.getNumberFormat();

    /* Variáveis do objeto */
    private Categoria categoria;
    private String nome;
    private String descricao;
    private int stock;
    private double preco;
    private int iva;
    private LocalDate validade;

    /* Construtor */
    public Produto(Categoria categoria, String nome, String descricao, int stock, double preco, int iva,
            LocalDate validade) {
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.stock = stock;
        this.preco = preco;
        this.iva = iva;
        this.validade = validade;
    }

    /* Retorna a categoria */
    public Categoria getCategoria() {
        return this.categoria;
    }

    /* Atribui a categoria */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /* Retorna o nome */
    public String getNome() {
        return this.nome;
    }

    /* Atribui o nome */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /* Retorna a descrição */
    public String getDescricao() {
        return descricao;
    }

    /* Atribui a descrição */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /* Retorna o stock */
    public int getStock() {
        return stock;
    }

    /* Atribui o stock */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /* Retorna o preço */
    public double getPreco() {
        return this.preco;
    }

    /* Atribui o preço */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /* Retorna o IVA */
    public int getIva() {
        return this.iva;
    }

    /* Atribui o IVA */
    public void setIva(int iva) {
        this.iva = iva;
    }

    /* Retorna a data de validade */
    public LocalDate getDate() {
        return this.validade;
    }

    /* Atribui a data de validade */
    public void setDate(LocalDate date) {
        this.validade = date;
    }

    /* Atribui a data de validade */
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    /* Retorna a data de validade */
    public LocalDate getValidade() {
        return this.validade;
    }

    /* Formata os dados do produto */
    @Override
    public String toString() {
        String str = "";

        str += "--- Categoria: " + (this.categoria == null ? "null" : this.categoria) + " ---" + "\n";
        str += "Nome: " + (this.nome == null ? "null" : this.nome) + "\n";
        str += "Descrição: " + (this.descricao == null ? "null" : this.descricao) + "\n";
        str += "Stock: " + this.stock + "\n";
        str += "Preço: " + numberFormat.format(this.preco) + "\n";
        str += "Iva: " + this.iva + "%" + "\n";
        str += "Validade: " + (this.validade == null ? "null" : this.validade) + "\n";

        return str;
    }
}