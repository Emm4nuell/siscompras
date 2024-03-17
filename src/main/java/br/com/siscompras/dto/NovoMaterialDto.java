package br.com.siscompras.dto;

import br.com.siscompras.entity.Material;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoMaterialDto {

    private Long id;

    @NotBlank(message = "Campo prioridade obrigatório!")
    private String prioridade;
    @NotBlank(message = "Campo descrição obrigatório!")
    private String descricao;
    private Long quantidade;
    private String observacao;
    private String arquivo;
    private boolean status;
    private String andamento;
    private double mediavalor;
    private double minvalor;
    private double maxvalor;


    private UsuarioDto usuarioDto;

    public static Material toMaterial(NovoMaterialDto dto){
        Material material = new Material();
        material.setPrioridade(dto.getPrioridade());
        material.setDescricao(dto.getDescricao());
        material.setQuantidade(dto.getQuantidade());
        material.setObservacao(dto.getObservacao());
        material.setArquivo(dto.getArquivo());
        material.setMinvalor(dto.getMinvalor());
        material.setMediavalor(dto.getMediavalor());
        material.setMaxvalor(dto.getMaxvalor());
        material.setUsuario(UsuarioDto.toUsuario(dto.getUsuarioDto()));

        return material;
    }

    public static NovoMaterialDto toMaterialDto(Material material){
        NovoMaterialDto dto = new NovoMaterialDto();
        dto.prioridade = material.getPrioridade();
        dto.descricao = material.getDescricao();
        dto.quantidade = material.getQuantidade();
        dto.observacao = material.getObservacao();
        dto.arquivo = material.getArquivo();
        dto.usuarioDto = UsuarioDto.toUsuarioDto(material.getUsuario());

        return dto;
    }

    public static List<NovoMaterialDto> toListCriarMaterialDto(List<Material> materiais){
        List<NovoMaterialDto> dtos = materiais.stream().map(x -> toMaterialDto(x)).collect(Collectors.toList());
        return dtos;
    }
}
