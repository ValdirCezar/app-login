package com.valdir.app.resources;

import com.valdir.app.models.Usuario;
import com.valdir.app.services.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioResourceTest {

    private static final Integer ID = 1;
    private static final String NOME = "Valdir Cezar";
    private static final String CPF = "488.484.130-13";
    private static final String EMAIL = "email@mail.com";
    private static final String SENHA = "123";

    @InjectMocks
    private UsuarioResource usuarioResource;

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarUsuarioDTO_QuandoChamarFindByIdTest() {
        Usuario usuario = new Usuario(ID, NOME, CPF, EMAIL, SENHA);

        Mockito.when(usuarioService.findById(Mockito.anyInt())).thenReturn(usuario);
        var response = usuarioResource.findById(ID);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody().getId(), usuario.getId());
        Assertions.assertEquals(response.getBody().getCpf(), usuario.getCpf());
        Assertions.assertNotNull(response);
    }
}






















