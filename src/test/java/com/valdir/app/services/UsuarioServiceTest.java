package com.valdir.app.services;

import com.valdir.app.models.Usuario;
import com.valdir.app.repositories.UsuarioRepository;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    private static final Integer ID = 1;
    private static final String NOME = "Valdir Cezar";
    private static final String CPF = "488.484.130-13";
    private static final String EMAIL = "email@mail.com";
    private static final String SENHA = "123";
    private Usuario usuario;

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(ID, NOME, CPF, EMAIL, SENHA);
    }

    @Test
    public void deveRetornarUsuario_QuandoChamarFindByIdComIdValidoTest() {
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
        Usuario response = usuarioService.findById(ID);

        Assertions.assertEquals(usuario.toString(), response.toString());
        Assertions.assertNotNull(response);
    }

    @Test
    public void deveRetornarErro_QuandoChamarFindByIdComIdInvalidoTest() {
        try {
            Mockito.when(usuarioService.findById(Mockito.anyInt()))
                    .thenThrow(new ObjectNotFoundException(
                            "Objeto não encontrado! Id: " + ID + ", Tipo: " + Usuario.class.getSimpleName()));
        } catch (ObjectNotFoundException ex) {
            Assertions.assertEquals(ex.getMessage(), "Objeto não encontrado! Id: " + 0 + ", Tipo: " + Usuario.class.getSimpleName());
        }
    }

    @Test
    public void deveRetornarListaDeUsuario_QuandoChamarFindAllTest() {
        List<Usuario> list = Collections.singletonList(usuario);
        Mockito.when(usuarioRepository.findAll()).thenReturn(list);

        List<Usuario> response = usuarioService.findAll();
        Assertions.assertEquals(response.get(0).toString(), list.get(0).toString());
    }

    @Test
    public void deveRetornarUsuario_QuandoCreateForChamadoTest() {
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);
        Usuario response = usuarioService.create(usuario);
        Assertions.assertEquals(usuario.toString(), response.toString());
    }

    @Test
    public void deveRetornarUsuario_QuandoUpdateForChamadoTest() {
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);

        Usuario response = usuarioService.update(1, usuario);
        Assertions.assertEquals(usuario.toString(), response.toString());
    }

}
