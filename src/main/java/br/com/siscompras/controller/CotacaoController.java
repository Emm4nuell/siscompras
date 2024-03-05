package br.com.siscompras.controller;

import br.com.siscompras.dto.CotacaoDto;
import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.service.CotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CotacaoDto dto){

        cotacaoService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<CotacaoDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cotacaoService.findAll());
    }
}
