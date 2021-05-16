package com.valdir.app.services;

import com.valdir.app.models.Usuario;
import com.valdir.app.models.dtos.UsuarioDTO;
import com.valdir.app.repositories.UsuarioRepository;
import com.valdir.app.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getSimpleName())
        );
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(UsuarioDTO objDTO) {
        objDTO.setId(null);
        return repository.save(new Usuario(objDTO));
    }

	public Usuario update(Integer id, @Valid UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(id);
		Usuario usuario = findById(id);
		usuario = new Usuario(usuarioDTO);
		return repository.save(usuario);
	}
}





















