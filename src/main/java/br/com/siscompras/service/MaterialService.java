package br.com.siscompras.service;

import br.com.siscompras.dto.MaterialDto;
import br.com.siscompras.entity.Material;
import br.com.siscompras.repository.MaterialRepository;
import br.com.siscompras.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void save(MaterialDto dto) {

        Material material = MaterialDto.toMaterial(dto);
        material.setStatus(true);
        materialRepository.save(material);
    }

    public List<MaterialDto> findAll() {
        List<Material> materiais = materialRepository.findAll();
        List<MaterialDto> dtos = materiais.stream().map((e) -> MaterialDto.toMaterialDto(e))
                .collect(Collectors.toList());
        return dtos;
    }


    public MaterialDto findById(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Material não disponível!"));
        return MaterialDto.toMaterialDto(material);
    }
}
