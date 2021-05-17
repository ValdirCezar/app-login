package com.valdir.app.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.app.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final Long SerialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Campo NOME é requerido")
    private String nome;

    @CPF
    @NotNull(message = "Campo CPF é requerido")
    private String cpf;

    @NotNull(message = "Campo E-MAIL é requerido")
    private String email;

    @JsonIgnore
    @NotNull(message = "Campo SENHA é requerido")
    private String senha;

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
    }

    public String getSenha() {
        return senha;
    }
}































