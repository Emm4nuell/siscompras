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
        MaterialNovoDto dto = new MaterialNovoDto(
                material.getId(),
                material.getDatacriacao(),
                material.getPrioridade(),
                material.getDescricao(),
                material.getQuantidade(),
                material.getObservacao(),
                material.getArquivo()
        );

        return  dto;
    }

    public static Material toMaterial(MaterialNovoDto dto){
        Material material = new Material(
                dto.getId(),
                dto.getDatacriacao(),
                null,
                dto.getPrioridade(),
                dto.getDescricao(),
                dto.getQuantidade(),
                true,
                null,
                null,
                null,
                null,
                dto.getObservacao(),
                dto.getArquivo(),
                null,
                null
        );

        return  material;
    }
}
