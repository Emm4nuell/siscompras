package br.com.siscompras.dto;

import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.entity.Material;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoDto {

    private Long id;
    //    @NotBlank(message = "Campo obrigatório!")
    private LocalDateTime datacriacao;
    private String descricao;
    private boolean status;
    private String arquivo;
    //    @NotBlank(message = "Campo obrigatório!")
    private BigDecimal preco;
    private BigDecimal frete;
    private BigDecimal precototal;
    private String quantidade;
    private String url;
    private String observacao;
    private EmpresaDto empresaDto;
    private MaterialDto materialDto;

    public static CotacaoDto toCotacaoDto(Cotacao cotacao) {
        CotacaoDto dto = new CotacaoDto();
        dto.setId(cotacao.getId());
        dto.setDatacriacao(cotacao.getDatacriacao());
        dto.setDescricao(cotacao.getDescricao());
        dto.setStatus(cotacao.isStatus());
        dto.setArquivo(cotacao.getArquivo());
        dto.setPreco(cotacao.getPreco());
        dto.setFrete(cotacao.getFrete());
//                dto.setPrecototal(cotacao.getPrecototal());
        dto.setQuantidade(cotacao.getQuantidade());
        dto.setUrl(cotacao.getUrl());
        dto.setObservacao(cotacao.getObservacao());
        dto.setEmpresaDto(EmpresaDto.toEmpresaDto(cotacao.getEmpresa()));
        return dto;
    }

    public static Cotacao toCotacao(CotacaoDto dto) {
        Cotacao cotacao = new Cotacao();

        cotacao.setId(dto.getId());
        cotacao.setDatacriacao(dto.getDatacriacao());
        cotacao.setStatus(dto.isStatus());
        cotacao.setArquivo(dto.getArquivo());
        cotacao.setPreco(dto.getPreco());
        cotacao.setFrete(dto.getFrete());
//        cotacao.setPrecototal(dto.getPrecototal().add(dto.getFrete()));
        cotacao.setQuantidade(dto.getQuantidade());
        cotacao.setUrl(dto.getUrl());
        cotacao.setEmpresa(EmpresaDto.toEmpresa(dto.getEmpresaDto()));
        cotacao.setMaterial(MaterialDto.toMaterial(dto.getMaterialDto()));

        return cotacao;
    }

    public static List<CotacaoDto> toListCotacaoDto(List<Cotacao> cotacoes) {
        List<CotacaoDto> dtos = cotacoes.stream().map((e) -> toCotacaoDto(e))
                .collect(Collectors.toList());
        return dtos;
    }
}
