package com.vieira.pluto.enums;

public enum SimNao {

    SIM("Sim", true),
    NAO("Não", false);

    private final String label;
    private final Boolean value;

    private SimNao(String label, Boolean value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getValue() {
        return value;
    }

}
