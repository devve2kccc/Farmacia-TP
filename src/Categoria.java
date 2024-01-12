public enum Categoria {

    /* Variaveis constantes */
    GERAL("Geral"),
    MEDICAMENTO("Medicamento"),
    COSMETICO("Cosmético"),
    HIGIENE("Higiene"),
    NUTRICIONAL("Nutricional"),
    EQUIPAMENTO_MEDICO("Equipamento Médico"),
    DIAGNOSTICO("Diagnóstico"),
    SAUDE_BUCAL("Saúde Bucal");

    /* descriçao */
    private String descricao;

    /* Descriçao */
    Categoria(String descricao) {
        this.descricao = descricao;
    }

    /* return descriçao */
    public String getDescricao() {
        return this.descricao;
    }
}
