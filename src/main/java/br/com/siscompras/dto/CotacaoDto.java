package br.com.siscompras.dto;

import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Empresa;
import br.com.siscompras.entity.Material;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoDto {

    private Long id;
    private String datacriacao;
    @NotBlank(message = "Campo descrição é obrigatório!")
    private String descricao;
    @NotBlank(message = "Campo vendedor é obrigatório!")
    private String vendedor;
    private boolean status;
    private String arquivo;
    @NotNull(message = "Campo preço é obrigatório!")
    private double preco;
    private double frete;
    private double precototal;
    private String quantidade;
    private String url;
    private String observacao;
    private EmpresaDto empresaDto;
    private MaterialDto materialDto;

    public static CotacaoDto toCotacaoDto(Cotacao cotacao) {
        CotacaoDto dto = new CotacaoDto();
        dto.setId(cotacao.getId());
        dto.setDatacriacao(cotacao.getDatacriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        dto.setDescricao(cotacao.getDescricao());
        dto.setVendedor(cotacao.getVendedor());
        dto.setStatus(cotacao.isStatus());
        dto.setArquivo(cotacao.getArquivo());
        dto.setPreco(cotacao.getPreco());
        dto.setFrete(cotacao.getFrete());
        dto.setPrecototal(cotacao.getPrecototal());
        dto.setQuantidade(cotacao.getQuantidade());
        dto.setUrl(cotacao.getUrl());
        dto.setObservacao(cotacao.getObservacao());
        dto.setEmpresaDto(EmpresaDto.toEmpresaDto(cotacao.getEmpresa()));
        return dto;
    }

    public static Cotacao toCotacao(CotacaoDto dto) {
        Cotacao cotacao = new Cotacao();

        cotacao.setId(dto.getId());
        cotacao.setDatacriacao(LocalDateTime.now());
        cotacao.setStatus(dto.isStatus());
        cotacao.setArquivo(dto.getArquivo());
        cotacao.setVendedor(dto.getVendedor());
        cotacao.setPreco(dto.getPreco());
        cotacao.setFrete(dto.getFrete());
        cotacao.setPrecototal(dto.getPreco() + dto.getFrete());
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
