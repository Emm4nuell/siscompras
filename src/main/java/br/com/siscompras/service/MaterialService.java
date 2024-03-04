package br.com.siscompras.service;

import br.com.siscompras.dto.MaterialDto;
import br.com.siscompras.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Transactional
    public void save(MaterialDto dto) {
        materialRepository.save(MaterialDto.toMaterial(dto));
    }
}
