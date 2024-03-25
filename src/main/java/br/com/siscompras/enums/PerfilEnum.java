package br.com.siscompras.enums;

import lombok.Getter;

@Getter
public enum PerfilEnum {

    ADMINISTRADOR(1, "ADMINISTRADOR"),
    COTACAO(2, "COTAÇÃO"),
    ENGENHARIA(3, "ENGENHARIA"),
    DIRETOR(4, "DIRETOR"),
    ADJUNTO(5, "ADJUNTO"),
    SECRETARIO(6, "SECRETARIO"),
    USUARIO(7, "USUARIO");

    private String perfil;
    private Integer id;

    PerfilEnum(Integer id, String perfil){
        this.id = id;
        this.perfil = perfil;
    }

    public static PerfilEnum toPerfilEnum(Integer id){
        if(id == null){
            return PerfilEnum.USUARIO;
        }

        for (PerfilEnum e: PerfilEnum.values()){
            if(e.id == id){
                return e;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido!");
    }
}
