package br.com.siscompras.service;

import br.com.siscompras.dto.EmpresaDto;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public void save(EmpresaDto dto) {
        Empresa opt = empresaRepository.findByCnpj(dto.getCnpj())
                .orElseThrow(() -> new NullPointerException("Empresa já cadastrada no sistema!"));

        Empresa empresa = EmpresaDto.toEmpresa(dto);
        empresa.setStatus(true);
        empresaRepository.save(empresa);
    }

    public List<EmpresaDto> findAll() {
        List<EmpresaDto> dtos = EmpresaDto.toListEmpresaDto(empresaRepository.findAll());
        return  dtos;
    }

    public EmpresaDto findById(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Empresa não cadastrada!"));
        return EmpresaDto.toEmpresaDto(empresa);
    }

    public EmpresaDto update(Long id, EmpresaDto dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Empresa não disponível"));
        dto.setId(empresa.getId());
        empresaRepository.save(EmpresaDto.toEmpresa(dto));
        return dto;
    }

    public void delete(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Empresa não disponível"));
        empresaRepository.deleteById(id);
    }
}
