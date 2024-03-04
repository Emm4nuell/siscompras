package br.com.siscompras.service;

import br.com.siscompras.dto.UsuarioDto;
import br.com.siscompras.entity.Usuario;
import br.com.siscompras.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void create(UsuarioDto dto) {

        Optional<Usuario> opt = usuarioRepository.findByCpf(dto.getCpf());

        if(opt.isPresent()){
            throw new NullPointerException("Usuário já cadastrado no sistema");
        }
        usuarioRepository.save(UsuarioDto.toUsuario(dto));
    }

    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> dtos = usuarios.stream()
                .map((e) -> UsuarioDto.toUsuarioDto(e)).collect(Collectors.toList());

        return dtos;
    }
}
