package com.vieira.pluto.enums;

public enum Mes {

    JANEIRO("Janeiro", 0),
    FEVEREIRO("Fevereiro", 1),
    MARCO("Março", 2),
    ABRIL("Abril", 3),
    MAIO("Maio", 4),
    JUNHO("Junho", 5),
    JULHO("Julho", 6),
    AGOSTO("Agosto", 7),
    SETEMBRO("Setembro", 8),
    OUTUBRO("Outubro", 9),
    NOVEMBRO("Novembro", 10),
    DEZEMBRO("Dezembro", 11);

    private final String label;
    private final Integer value;

    private Mes(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }

    public static Mes getByValue(Integer value) {
        for (Mes mes : Mes.values()){
            if (mes.value.equals(value)){
                return mes;
            }
        }
        return null;
    }
    public static Mes getByLabel(String label) {
        for (Mes mes : Mes.values()){
            if (mes.label.equals(label)){
                return mes;
            }
        }
        return null;
    }

}
