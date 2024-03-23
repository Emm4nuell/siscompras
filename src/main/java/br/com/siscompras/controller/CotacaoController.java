package br.com.siscompras.controller;

import br.com.siscompras.dto.CotacaoDto;
import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.service.CotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CotacaoDto dto) {

        cotacaoService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @GetMapping
//    public ResponseEntity<List<CotacaoDto>> findAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(cotacaoService.findAll());
//    }

    @GetMapping
    public ResponseEntity<List<CotacaoDto>> findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(cotacaoService.findAllPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotacaoDto> findById(@PathVariable Long id){
        CotacaoDto dto = cotacaoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CotacaoDto> update(@PathVariable Long id, @RequestBody CotacaoDto dto){
        CotacaoDto cotacaoDto = cotacaoService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(cotacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cotacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
