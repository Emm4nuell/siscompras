package br.com.siscompras.service;

import br.com.siscompras.dto.UsuarioDto;
import br.com.siscompras.entity.Usuario;
import br.com.siscompras.enums.PerfilEnum;
import br.com.siscompras.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void create(UsuarioDto dto) {

        Optional<Usuario> opt = usuarioRepository.findByCpf(dto.getCpf());

        if (opt.isPresent()) {
            throw new NullPointerException("Usuário já cadastrado no sistema");
        }
        Usuario usuario = UsuarioDto.toUsuario(dto);
        usuario.setDatacadastro(LocalDateTime.now());
        usuario.setStatus(true);
        usuario.setSenha(encoder.encode(dto.getSenha()));
        usuarioRepository.save(usuario);
    }

    public List<UsuarioDto> findAll() {
        return UsuarioDto.toListUsuarioDto(usuarioRepository.findAll());
    }

    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("Usuário não localizado!"));
        return UsuarioDto.toUsuarioDto(usuario);
    }

    public UsuarioDto update(Long id, UsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new NullPointerException("Usuario não localizado!"));

        dto.setId(usuario.getId());
        usuarioRepository.save(UsuarioDto.toUsuario(dto));
        return dto;
    }

    public List<UsuarioDto> findAllPage(Pageable pageable) {
        return UsuarioDto.toListUsuarioDto(usuarioRepository.findAll(pageable).toList());
    }
}
