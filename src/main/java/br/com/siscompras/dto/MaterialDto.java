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
public class MaterialDto {

    private Long id;
    private LocalDateTime datacriacao;
    private Date dataconclusao;
    private String prioridade;
    private String descricao;
    private Long quantidade;
    private boolean status;
    private String andamento;
    private String mediavalor;
    private String minvalor;
    private String maxvalor;
    private String observacao;
    private String arquivo;

    public static MaterialDto toMaterial(Material material){
        MaterialDto dto = new MaterialDto(
                material.getId(),
                material.getDatacriacao(),
                material.getDataconclusao(),
                material.getPrioridade(),
                material.getDescricao(),
                material.getQuantidade(),
                material.isStatus(),
                material.getAndamento(),
                material.getMediavalor(),
                material.getMinvalor(),
                material.getMaxvalor(),
                material.getObservacao(),
                material.getArquivo()
        );

        return  dto;
    }

    public static Material toMaterialDto(MaterialDto dto){
        Material material = new Material(
                dto.getId(),
                dto.getDatacriacao(),
                dto.getDataconclusao(),
                dto.getPrioridade(),
                dto.getDescricao(),
                dto.getQuantidade(),
                dto.isStatus(),
                dto.getAndamento(),
                dto.getMediavalor(),
                dto.getMinvalor(),
                dto.getMaxvalor(),
                dto.getObservacao(),
                dto.getArquivo(),
                null,
                null
        );

        return  material;
    }
}
