package br.com.siscompras.dto;

import br.com.siscompras.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<CotacaoDto> cotacaoDtos;

    public static MaterialDto toMaterialDto(Material material){
        MaterialDto dto = new MaterialDto();
                dto.setId(material.getId());
                dto.setDatacriacao(material.getDatacriacao());
                dto.setDataconclusao(material.getDataconclusao());
                dto.setPrioridade(material.getPrioridade());
                dto.setDescricao(material.getDescricao());
                dto.setQuantidade(material.getQuantidade());
                dto.setStatus(material.isStatus());
                dto.setAndamento(material.getAndamento());
                dto.setMediavalor(material.getMediavalor());
                dto.setMinvalor(material.getMinvalor());
                dto.setMaxvalor(material.getMaxvalor());
                dto.setObservacao(material.getObservacao());
                dto.setArquivo(material.getArquivo());
                dto.setCotacaoDtos(CotacaoDto.toListCotacaoDto(material.getCotacoes()));
        return  dto;
    }

    public static Material toMaterial(MaterialDto dto){
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

    public static List<MaterialDto> toListMaterialDto(List<Material> materiais){
        List<MaterialDto> dtos = materiais.stream().map((e) -> toMaterialDto(e))
                .collect(Collectors.toList());
        return dtos;
    }
}
