public enum Categoria {

    /* Variáveis constantes */
    GERAL("Geral"),
    MEDICAMENTO("Medicamento"),
    COSMETICO("Cosmético"),
    HIGIENE("Higiene"),
    NUTRICIONAL("Nutricional"),
    EQUIPAMENTO_MEDICO("Equipamento Médico"),
    DIAGNOSTICO("Diagnóstico"),
    SAUDE_BUCAL("Saúde Bucal");

    /* Descrição da categoria */
    private String descricao;

    /**
     * Construtor para a enum Categoria.
     * 
     * @param descricao A descrição da categoria.
     */
    Categoria(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição da categoria.
     * 
     * @return A descrição da categoria.
     */
    public String getDescricao() {
        return this.descricao;
    }
}