package com.valdir.app.resources;

import com.valdir.app.models.Usuario;
import com.valdir.app.models.dtos.UsuarioDTO;
import com.valdir.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    /**
     * Cria um novo Usuario
     * @param objDTO
     * @return URI
     * @return UsuarioDTO
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO objDTO) {
        Usuario usuario = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    /**
     * Atualiza um Usuario
     * @param id
     * @param usuarioDTO
     * @return usuarioDTO
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
    	Usuario newObj = service.update(id, usuarioDTO);
    	return ResponseEntity.ok().body(new UsuarioDTO(newObj));
    }
    
}






















