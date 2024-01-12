import java.text.NumberFormat;
import java.time.LocalDate;

public class Produto {
    NumberFormat numberFormat = Utils.getNumberFormat();

    private Categoria categoria;
    private String nome;
    private String descricao;
    private int stock;
    private double preco;
    private int iva;
    private LocalDate validade;

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

    public void calculaDose(int idade) {
        double dose = 0;

        if (idade < 18) {
            dose = 0.5;
        } else if (idade >= 18 && idade < 40) {
            dose = 1.0;
        } else if (idade >= 40 && idade < 60) {
            dose = 1.5;
        } else {
            dose = 2.0;
        }

        System.out.println("Dose calculada para idade " + idade + ": " + dose + "g");
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getIva() {
        return this.iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public LocalDate getDate() {
        return this.validade;
    }

    public void setDate(LocalDate date) {
        this.validade = date;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return this.validade;
    }

    @Override
    public String toString() {
        String str = "";

        str += "--- Categoria: " + (this.categoria == null ? "null" : this.categoria) + " ---" + "\n";
        str += "Nome: " + (this.nome == null ? "null" : this.nome) + "\n";
        str += "Descrição: " + (this.descricao == null ? "null" : this.descricao) + "\n";
        str += "Stock: " + this.stock + "\n";
        str += "Preço: " + this.preco + "\n";
        str += "Iva: " + this.iva + "%" + "\n";
        str += "Validade: " + (this.validade == null ? "null" : this.validade) + "\n";

        return str;
    }
}
