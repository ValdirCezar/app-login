package com.valdir.app.resources;

import com.valdir.app.configurations.ModelMapperConfig;
import com.valdir.app.models.Usuario;
import com.valdir.app.models.dtos.UsuarioDTO;
import com.valdir.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private ModelMapperConfig modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findByOd(@PathVariable Integer id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(modelMapper.modelMapper().map(usuario, UsuarioDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDTO = list.stream().map(obj ->
                modelMapper.modelMapper().map(obj, UsuarioDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
