package br.com.siscompras.dto;

import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Empresa;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoDto {

    private Long id;
    @NotBlank(message = "Campo obrigatório!")
    private LocalDateTime datacriacao;
    private boolean status;
    private String arquivo;
    @NotBlank(message = "Campo obrigatório!")
    private String preco;
    private String frete;
    private String quantidade;
    private String url;
    private EmpresaDto empresaDto;

    public static CotacaoDto toCotacaoDto(Cotacao cotacao){
        CotacaoDto dto = new CotacaoDto(
                cotacao.getId(),
                cotacao.getDatacriacao(),
                cotacao.isStatus(),
                cotacao.getArquivo(),
                cotacao.getPreco(),
                cotacao.getFrete(),
                cotacao.getQuantidade(),
                cotacao.getUrl(),
                EmpresaDto.toEmpresaDto(cotacao.getEmpresa())
        );

        return dto;
    }

    public static Cotacao toCotacao(CotacaoDto dto){
        Cotacao cotacao = new Cotacao();

        cotacao.setId(dto.getId());
        cotacao.setDatacriacao(dto.getDatacriacao());
        cotacao.setStatus(dto.isStatus());
        cotacao.setArquivo(dto.getArquivo());
        cotacao.setPreco(dto.getPreco());
        cotacao.setFrete(dto.getFrete());
        cotacao.setQuantidade(dto.getQuantidade());
        cotacao.setUrl(dto.getUrl());
        cotacao.setEmpresa(EmpresaDto.toEmpresa(dto.getEmpresaDto()));

        return cotacao;
    }
}
