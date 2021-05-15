package com.valdir.app.services;

import com.valdir.app.models.Usuario;
import com.valdir.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void instanciaDB() {
        Usuario u1 = new Usuario(null, "Valdir Cezar", "488.484.130-13", "valdir@mail.com", "123");
        Usuario u2 = new Usuario(null, "João Pedro", "227.235.780-62", "joao@mail.com", "123");

        usuarioRepository.saveAll(Arrays.asList(u1, u2));
    }
}
