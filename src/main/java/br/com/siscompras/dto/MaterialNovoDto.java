package br.com.siscompras.dto;

import br.com.siscompras.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialNovoDto {

    private Long id;
    private LocalDateTime datacriacao;
    private String prioridade;
    private String descricao;
    private Long quantidade;
    private String observacao;
    private String arquivo;

    public static MaterialNovoDto toMaterialNovoDto(Material material){
        MaterialNovoDto dto = new MaterialNovoDto();
                dto.setId(material.getId());
                dto.setDatacriacao(material.getDatacriacao());
                dto.setPrioridade(material.getPrioridade());
                dto.setDescricao(material.getDescricao());
                dto.setQuantidade(material.getQuantidade());
                dto.setObservacao(material.getObservacao());
                dto.setArquivo(material.getArquivo());
        return  dto;
    }

    public static Material toMaterial(MaterialNovoDto dto){
        Material material = new Material();
                material.setId(dto.getId());
                material.setDatacriacao(dto.getDatacriacao());
                material.setPrioridade(dto.getPrioridade());
                material.setDescricao(dto.getDescricao());
                material.setQuantidade(dto.getQuantidade());
                material.setObservacao(dto.getObservacao());
                material.setArquivo(dto.getArquivo());

        return  material;
    }
}
