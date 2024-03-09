package br.com.siscompras.controller;

import br.com.siscompras.dto.MaterialDto;
import br.com.siscompras.dto.MaterialNovoDto;
import br.com.siscompras.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid MaterialDto dto){

        materialService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/novomaterial")
    public ResponseEntity<Void> novoMaterial(@RequestBody @Valid MaterialNovoDto dto){

        materialService.saveNovo(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping
    public ResponseEntity<List<MaterialDto>> findAll(){
        List<MaterialDto> dtos = materialService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> findById(@PathVariable Long id){
        MaterialDto dto = materialService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
