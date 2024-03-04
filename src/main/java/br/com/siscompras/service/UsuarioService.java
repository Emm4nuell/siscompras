package br.com.siscompras.service;

import br.com.siscompras.dto.UsuarioDto;
import br.com.siscompras.entity.Usuario;
import br.com.siscompras.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void create(UsuarioDto dto) {

        Optional<Usuario> opt = usuarioRepository.findByCpf(dto.getCpf());

        opt.orElseThrow(() -> new NullPointerException("Usuario já cadastrado no sistema!"));

        usuarioRepository.save(UsuarioDto.toUsuario(dto));
    }
}
