package br.com.siscompras.service;

import br.com.siscompras.dto.CotacaoDto;
import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.entity.Material;
import br.com.siscompras.repository.CotacaoRepository;
import br.com.siscompras.repository.EmpresaRepository;
import br.com.siscompras.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EmpresaRepository empresaRepository;


    @Transactional
    public CotacaoDto save(CotacaoDto dto) {

        Cotacao cotacao = CotacaoDto.toCotacao(dto);
        Optional<Empresa> optEmpresa = empresaRepository.findByCnpj(dto.getEmpresaDto().getCnpj());

        if (optEmpresa.isPresent()) {
            System.err.println("Contem o cnpj no sistema!");
//            cotacao.setEmpresa(optEmpresa.get());
            cotacaoRepository.save(cotacao);
            return dto;
        }
        System.err.println("Execultado fora do sistema!");
        empresaRepository.save(cotacao.getEmpresa());
        cotacaoRepository.save(cotacao);
        return dto;
    }

    public List<CotacaoDto> findAll() {
        return CotacaoDto.toListCotacaoDto(cotacaoRepository.findAll());
    }
}
