package br.com.siscompras.service;

import br.com.siscompras.dto.CotacaoDto;
import br.com.siscompras.dto.MaterialDto;
import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.entity.Material;
import br.com.siscompras.repository.CotacaoRepository;
import br.com.siscompras.repository.EmpresaRepository;
import br.com.siscompras.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
    public CotacaoDto save(CotacaoDto dto) {
            Cotacao cotacao = CotacaoDto.toCotacao(dto);
            Optional<Empresa> optEmpresa = empresaRepository.findByCnpj(dto.getEmpresaDto().getCnpj());
            cotacao.setStatus(true);

            if (optEmpresa.isPresent()) {
                cotacao.getEmpresa().setId(optEmpresa.get().getId());
                cotacaoRepository.save(cotacao);
                updatePreco(dto.getMaterialDto().getId());
                return dto;
            }
            empresaRepository.save(cotacao.getEmpresa());
            cotacaoRepository.save(cotacao);
            updatePreco(dto.getMaterialDto().getId());
            return dto;
    }

    public List<CotacaoDto> findAll() {
        return CotacaoDto.toListCotacaoDto(cotacaoRepository.findAll());
    }

    public CotacaoDto findById(Long id) {
        Cotacao cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new NullPointerException("Cotação indisponível"));
        return CotacaoDto.toCotacaoDto(cotacao);
    }

    public void updatePreco(Long id) {
        double total = 0;
        int divisor = 0;
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Material não encontrado!"));

        List<Cotacao> cotacoes = cotacaoRepository.findAll();

        for(Cotacao c : cotacoes){
            total += c.getPrecototal();
            divisor ++;
        }
        material.setId(material.getId());
        material.setMediavalor(total/divisor);
        materialRepository.save(material);
    }
}
