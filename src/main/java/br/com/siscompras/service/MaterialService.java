package br.com.siscompras.service;

import br.com.siscompras.dto.MaterialDto;
import br.com.siscompras.dto.MaterialNovoDto;
import br.com.siscompras.entity.Material;
import br.com.siscompras.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Transactional
    public void save(MaterialDto dto) {
        materialRepository.save(MaterialDto.toMaterial(dto));
    }

    public List<MaterialDto> findAll() {
        List<Material> materiais = materialRepository.findAll();
        List<MaterialDto> dtos = materiais.stream().map((e) -> MaterialDto.toMaterialDto(e))
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public void saveNovo(MaterialNovoDto dto) {
        materialRepository.save(MaterialNovoDto.toMaterial(dto));
    }
}
