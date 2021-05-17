package com.valdir.app.resources;

import com.valdir.app.models.Usuario;
import com.valdir.app.models.dtos.UsuarioDTO;
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
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioResourceTest {

    private static final Integer ID = 1;
    private static final String NOME = "Valdir Cezar";
    private static final String CPF = "488.484.130-13";
    private static final String EMAIL = "email@mail.com";
    private static final String SENHA = "123";
    private Usuario usuario;

    @InjectMocks
    private UsuarioResource usuarioResource;

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(ID, NOME, CPF, EMAIL, SENHA);
    }

    @Test
    public void deveRetornarUsuarioDTO_QuandoChamarFindByIdTest() {
        Mockito.when(usuarioService.findById(Mockito.anyInt())).thenReturn(usuario);
        ResponseEntity<UsuarioDTO> response = usuarioResource.findById(ID);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody().getId(), usuario.getId());
        Assertions.assertEquals(response.getBody().getCpf(), usuario.getCpf());
        Assertions.assertNotNull(response);
    }

    @Test
    public void deveRetornarListaDeUsuarioDTO_QuandoChamarFindAllTest() {
        List<Usuario> list = Collections.singletonList(usuario);

        Mockito.when(usuarioService.findAll()).thenReturn(list);
        ResponseEntity<List<UsuarioDTO>> response = usuarioResource.findAll();

        Assertions.assertEquals(list.get(0).getId(), response.getBody().get(0).getId());
        Assertions.assertEquals(UsuarioDTO.class.getSimpleName(), response.getBody().get(0).getClass().getSimpleName());
        Assertions.assertEquals(list.size(), response.getBody().size());
        Assertions.assertEquals(list.stream().count(), response.getBody().stream().count());
    }

    @Test
    public void deveRetornarStatus201_QuandoChamarCreateTest() {
        Mockito.when(usuarioService.create(Mockito.any())).thenReturn(usuario);
        ResponseEntity<UsuarioDTO> response = usuarioResource.create(usuario);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void deveRetornarStatus200_QuandoChamarUpdateTest() {
        Mockito.when(usuarioService.update(Mockito.any(), Mockito.any())).thenReturn(usuario);

        ResponseEntity<UsuarioDTO> response = usuarioResource.update(ID, usuario);

        Assertions.assertEquals(UsuarioDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(usuario.getId(), response.getBody().getId());
        Assertions.assertEquals(usuario.getCpf(), response.getBody().getCpf());
        Assertions.assertEquals(usuario.getSenha(), response.getBody().getSenha());
    }

    @Test
    public void deveRetornarStatus204_QuandoChamarDeleteTest() {
        Mockito.doNothing().when(usuarioService).delete(Mockito.any());
        ResponseEntity<Void> response = usuarioResource.delete(ID);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}






















