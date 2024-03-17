package br.com.siscompras.dto;

import br.com.siscompras.entity.Material;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {

    private Long id;
    private String datacriacao;
    private Date dataconclusao;
    @NotBlank(message = "Campo prioridade é obrigatório!")
    private String prioridade;
    @NotBlank(message = "Campo descrição é obrigatório!")
    private String descricao;
    private Long quantidade;
    private boolean status;
    private String andamento;
    private double mediavalor;
    private double minvalor;
    private double maxvalor;
    private String observacao;
    private String arquivo;
    private List<CotacaoDto> cotacaoDtos;

    private UsuarioDto usuarioDto;

    public static MaterialDto toMaterialDto(Material material) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        MaterialDto dto = new MaterialDto();
        dto.setId(material.getId());
        dto.setDatacriacao(material.getDatacriacao().format(formatter));
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
//        dto.setUsuarioDto(UsuarioDto.toUsuarioDto(material.getUsuario()));
        return dto;
    }

    public static Material toMaterial(MaterialDto dto) {

        Material material = new Material();
        material.setId(dto.getId());
        material.setDescricao(dto.getDescricao());
        material.setDataconclusao(dto.getDataconclusao());
        material.setPrioridade(dto.getPrioridade());
        material.setDescricao(dto.getDescricao());
        material.setQuantidade(dto.getQuantidade());
        material.setStatus(dto.isStatus());
        material.setAndamento(dto.getAndamento());
        material.setMediavalor(dto.getMediavalor());
        material.setMinvalor(dto.getMinvalor());
        material.setMaxvalor(dto.getMaxvalor());
        material.setObservacao(dto.getObservacao());
        material.setArquivo(dto.getArquivo());
        return material;
    }

    public static List<MaterialDto> toListMaterialDto(List<Material> materiais) {
        List<MaterialDto> dtos = materiais.stream().map((e) -> toMaterialDto(e))
                .collect(Collectors.toList());
        return dtos;
    }
}
