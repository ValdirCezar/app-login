package com.valdir.app.resources.services;

import com.valdir.app.models.Usuario;
import com.valdir.app.repositories.UsuarioRepository;
import com.valdir.app.services.UsuarioService;
import com.valdir.app.services.exceptions.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    private static final Integer ID = 1;
    private static final String NOME = "Valdir Cezar";
    private static final String CPF = "488.484.130-13";
    private static final String EMAIL = "email@mail.com";
    private static final String SENHA = "123";

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarUsuarioTest() {
        Usuario usuario = new Usuario(ID, NOME, CPF, EMAIL, SENHA);
        Optional<Usuario> optional = Optional.of(usuario);

        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(optional);
        Usuario response = usuarioService.findById(ID);

        Assertions.assertEquals(response.getId(), ID);
        Assertions.assertEquals(response.getNome(), usuario.getNome());
        Assertions.assertEquals(response.getCpf(), usuario.getCpf());
        Assertions.assertEquals(response.getEmail(), usuario.getEmail());
        Assertions.assertEquals(response.getSenha(), usuario.getSenha());
        Assertions.assertNotNull(response);
    }

    @Test
    public void deveRetornarErroTest() {
        try {
            Mockito.when(usuarioService.findById(Mockito.anyInt()))
                    .thenThrow(new ObjectNotFoundException(
                            "Objeto não encontrado! Id: " + ID + ", Tipo: " + Usuario.class.getSimpleName()));
        } catch (ObjectNotFoundException ex) {
            Assertions.assertEquals(ex.getMessage(), "Objeto não encontrado! Id: " + 0 + ", Tipo: " + Usuario.class.getSimpleName());
        }
    }

}
