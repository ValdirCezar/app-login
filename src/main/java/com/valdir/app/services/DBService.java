package com.valdir.app.services;

import com.valdir.app.models.Produto;
import com.valdir.app.models.Usuario;
import com.valdir.app.models.enums.Perfil;
import com.valdir.app.repositories.ProdutoRepository;
import com.valdir.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {
        Usuario u1 = new Usuario(null, "Valdir Cezar", "488.484.130-13", "valdir@mail.com", encoder.encode("123"));
        u1.addPerfil(Perfil.ADMIN);

        Usuario u2 = new Usuario(null, "João Pedro", "227.235.780-62", "joao@mail.com", encoder.encode("123"));

        Produto p1 = new Produto(null, "SmarTV", "SmarTV OLED");
        Produto p2 = new Produto(null, "Celular", "Samsung A21s");
        Produto p3 = new Produto(null, "Óculos", "Óculos de descanso");
        Produto p4 = new Produto(null, "Notebook", "Dell Ultra power megatom");

        usuarioRepository.saveAll(Arrays.asList(u1, u2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
    }
}
