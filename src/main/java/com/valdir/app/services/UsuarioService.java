package com.valdir.app.services;

import com.valdir.app.models.Usuario;
import com.valdir.app.repositories.UsuarioRepository;
import com.valdir.app.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getSimpleName())
        );
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(Usuario obj) {
        obj.setId(null);
        return repository.save(obj);
    }

	public Usuario update(Integer id, @Valid Usuario obj) {
        obj.setId(id);
        Usuario usuario = findById(id);
        usuario = mapper.map(obj, Usuario.class);
        return repository.save(usuario);
	}

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}





















