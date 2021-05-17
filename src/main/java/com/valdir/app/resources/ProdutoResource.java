package com.valdir.app.resources;

import com.valdir.app.models.Produto;
import com.valdir.app.models.dtos.ProdutoDTO;
import com.valdir.app.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    /**
     * Busca um Produto pelo id
     * @param id
     * @return ProdutoDTO
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok().body(new ProdutoDTO(produto));
    }

    /**
     * Lista todos os Produtos do banco
     * @return List<ProdutoDTO>
     */
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> list = service.findAll();
        List<ProdutoDTO> listDTO = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    /**
     * Cria um novo Produto
     * @param obj
     * @return URI
     * @return ProdutoDTO
     */
    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody Produto obj) {
        Produto produto = service.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Atualiza um Produto
     * @param id
     * @param obj
     * @return produtoDTO
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody Produto obj) {
        Produto newObj = service.update(id, obj);
        return ResponseEntity.ok().body(new ProdutoDTO(newObj));
    }

    /**
     * Deleta um Produto por id
     * @param id do Produto a ser deletado
     * @return noContent
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
