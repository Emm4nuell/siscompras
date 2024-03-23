package br.com.siscompras.controller;

import br.com.siscompras.dto.UsuarioDto;
import br.com.siscompras.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UsuarioDto dto){
        usuarioService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

//    @GetMapping
//    public ResponseEntity<List<UsuarioDto>> findAll(){
//        List<UsuarioDto> dto = usuarioService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(dto);
//    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAllPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto dto){
        UsuarioDto usuarioDto = usuarioService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
    }
}
