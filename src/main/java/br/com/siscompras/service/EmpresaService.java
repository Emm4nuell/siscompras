package br.com.siscompras.service;

import br.com.siscompras.dto.EmpresaDto;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public void save(EmpresaDto dto) {
        empresaRepository.save(EmpresaDto.toEmpresa(dto));
    }

    public List<EmpresaDto> findAll() {
        List<EmpresaDto> dtos = EmpresaDto.toListEmpresaDto(empresaRepository.findAll());
        return  dtos;
    }
}
