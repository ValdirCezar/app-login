package com.valdir.app.resources;

import com.valdir.app.models.Usuario;
import com.valdir.app.models.dtos.UsuarioDTO;
import com.valdir.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    /**
     * Busca um Usuario pelo id
     * @param id
     * @return UsuarioDTO
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(usuario));
    }

    /**
     * Lista todos os Usuarios do banco
     * @return List<UsuarioDTO>
     */

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    /**
     * Cria um novo Usuario
     * @param obj
     * @return URI
     * @return UsuarioDTO
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody Usuario obj) {
        Usuario usuario = service.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Atualiza um Usuario
     * @param id
     * @param obj
     * @return usuarioDTO
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @Valid @RequestBody Usuario obj) {
    	Usuario newObj = service.update(id, obj);
    	return ResponseEntity.ok().body(new UsuarioDTO(newObj));
    }

    /**
     * Deleta um Usuario por id
      * @param id do Usuario a ser deletado
     * @return noContent
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}






















