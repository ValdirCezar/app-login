package com.valdir.app.models;

import com.valdir.app.models.dtos.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario implements Serializable {
    private static final Long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo NOME é requerido")
    private String nome;

    @CPF
    @Column(unique = true)
    @NotNull(message = "Campo CPF é requerido")
    private String cpf;

    @Column(unique = true)
    @NotNull(message = "Campo E-MAIL é requerido")
    private String email;

    @NotNull(message = "Campo SENHA é requerido")
    private String senha;

    public Usuario(UsuarioDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
    }
}
