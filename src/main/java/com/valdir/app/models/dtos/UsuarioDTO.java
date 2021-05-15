package com.valdir.app.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.app.configurations.ModelMapperConfig;
import com.valdir.app.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final Long SerialVersionUID = 1L;

    @Autowired
    private ModelMapperConfig modelMapper;

    private Integer id;
    private String nome;

    @CPF
    private String cpf;
    private String email;

    @JsonIgnore
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        UsuarioDTO objDTO =  modelMapper.modelMapper().map(usuario, UsuarioDTO.class);
    }
}
