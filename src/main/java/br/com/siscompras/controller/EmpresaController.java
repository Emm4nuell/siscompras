package br.com.siscompras.controller;

import br.com.siscompras.dto.EmpresaDto;
import br.com.siscompras.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid EmpresaDto dto){
        empresaService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpresaDto> update(@PathVariable Long id, @RequestBody EmpresaDto dto){
        EmpresaDto empresadto = empresaService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(empresadto);
    }
}
