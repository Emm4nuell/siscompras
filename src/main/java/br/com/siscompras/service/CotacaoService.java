package br.com.siscompras.service;

import br.com.siscompras.dto.CotacaoDto;
import br.com.siscompras.dto.MaterialDto;
import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.entity.Material;
import br.com.siscompras.repository.CotacaoRepository;
import br.com.siscompras.repository.EmpresaRepository;
import br.com.siscompras.repository.MaterialRepository;
import br.com.siscompras.service.strategy.MaiorValor;
import br.com.siscompras.service.strategy.MediaValor;
import br.com.siscompras.service.strategy.MenorValor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;


    @Transactional
    public void save(CotacaoDto dto) {
            Cotacao cotacao = CotacaoDto.toCotacao(dto);
            Optional<Empresa> optEmpresa = empresaRepository.findByCnpj(dto.getEmpresaDto().getCnpj());
            cotacao.setStatus(true);

            if (optEmpresa.isPresent()) {
                cotacao.getEmpresa().setId(optEmpresa.get().getId());
                cotacaoRepository.save(cotacao);
                updatePreco(dto.getMaterialDto().getId());
            }else {
                empresaRepository.save(cotacao.getEmpresa());
                cotacaoRepository.save(cotacao);
                updatePreco(dto.getMaterialDto().getId());
            }
    }

    public List<CotacaoDto> findAll() {
        return CotacaoDto.toListCotacaoDto(cotacaoRepository.findAll());
    }

    public CotacaoDto findById(Long id) {
        Cotacao cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new NullPointerException("Cotação indisponível"));
        return CotacaoDto.toCotacaoDto(cotacao);
    }

    public void updatePreco(Long id) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Material não encontrado!"));

        Map<String, CalcularPrecoStrategy> map = Map.of(
                "max", new MaiorValor(),
                "media", new MediaValor(),
                "min", new MenorValor()
        );

        material.setId(material.getId());
        material.setMediavalor(map.get("media").CalcularPreco(material.getCotacoes()));
        material.setMaxvalor(map.get("max").CalcularPreco(material.getCotacoes()));
        material.setMinvalor(map.get("min").CalcularPreco(material.getCotacoes()));
        materialRepository.save(material);
    }

    public CotacaoDto update(Long id, CotacaoDto dto) {
        Cotacao d = cotacaoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Cotação não encontrada!"));
        dto.setId(d.getId());
        cotacaoRepository.save(CotacaoDto.toCotacao(dto));
        return dto;
    }

    @Transactional
    public void delete(Long id) {
        Cotacao cotacao = cotacaoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Cotação não encontrada!"));
        cotacaoRepository.deleteById(id);
    }

    public List<CotacaoDto> findAllPage(Pageable pageable) {
        List<CotacaoDto> dtos = cotacaoRepository.findAll(pageable).stream()
                .map((e) -> CotacaoDto.toCotacaoDto(e)).collect(Collectors.toList());
        return dtos;
    }
}
