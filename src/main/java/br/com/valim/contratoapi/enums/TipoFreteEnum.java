package br.com.valim.contratoapi.enums;

public enum TipoFreteEnum {

    CIF("1"),
    FOB("2"),
    SEM_FRETE("9");

    private String tipo;

    private TipoFreteEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
