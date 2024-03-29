package br.com.siscompras.service;

import br.com.siscompras.dto.UsuarioDto;
import br.com.siscompras.entity.Usuario;
import br.com.siscompras.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private PasswordEncoder encoder;

    private Optional<UsuarioDto> optusuarioDto;

    private UsuarioDto usuarioDto;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        //O this esta referenciando ao UsuarioService onde esta escrito o @InjectMocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void findAllPage() {
    }
}