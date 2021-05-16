package com.valdir.app.models.dtos;

import com.valdir.app.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
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

    @NotNull(message = "Campo SENHA é requerido")
    private String senha;

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
    }

}































