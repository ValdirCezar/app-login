package com.valdir.app.models.dtos;

import com.valdir.app.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
    }

}
