package com.valdir.app.models.enums;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), USER(1, "ROLE_USER");

    private Integer cod;
    private String descricao;

    Perfil(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(Perfil x : Perfil.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida! " + cod);
    }
}
