package com.valdir.app.services;

import com.valdir.app.models.Produto;
import com.valdir.app.repositories.ProdutoRepository;
import com.valdir.app.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public Produto findById(Integer id) {
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getSimpleName())
        );
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto create(Produto obj) {
        obj.setId(null);
        return repository.save(obj);
    }

	public Produto update(Integer id, @Valid Produto obj) {
        obj.setId(id);
        Produto produto = findById(id);
        produto = mapper.map(obj, Produto.class);
        return repository.save(produto);
	}

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}





















