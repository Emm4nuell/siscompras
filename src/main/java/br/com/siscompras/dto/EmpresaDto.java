package br.com.siscompras.dto;

import br.com.siscompras.entity.Empresa;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {

    private Long id;
    @NotBlank(message = "Campo CNPJ obrigatório!")
    @CNPJ(message = "CNPJ inválido!")
    private String cnpj;
    private String nome;
    private String fantasia;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String municipio;
    private String uf;
    private String email;
    private String telefone;
    private String celular;
    private boolean status;

    public static Empresa toEmpresa(EmpresaDto dto) {
        Empresa empresa = new Empresa();

        empresa.setId(dto.getId());
        empresa.setCnpj(dto.getCnpj());
        empresa.setNome(dto.getNome());
        empresa.setFantasia(dto.getFantasia());
        empresa.setLogradouro(dto.getLogradouro());
        empresa.setNumero(dto.getNumero());
        empresa.setComplemento(dto.getComplemento());
        empresa.setCep(dto.getCep());
        empresa.setBairro(dto.getBairro());
        empresa.setMunicipio(dto.getMunicipio());
        empresa.setUf(dto.getUf());
        empresa.setEmail(dto.getEmail());
        empresa.setTelefone(dto.getTelefone());
        empresa.setCelular(dto.getCelular());
        empresa.setStatus(dto.isStatus());

        return empresa;
    }

    public static EmpresaDto toEmpresaDto(Empresa empresa) {
        EmpresaDto dto = new EmpresaDto();
        dto.id = empresa.getId();
        dto.cnpj = empresa.getCnpj();
        dto.nome = empresa.getNome();
        dto.fantasia = empresa.getFantasia();
        dto.logradouro = empresa.getLogradouro();
        dto.numero = empresa.getNumero();
        dto.complemento = empresa.getComplemento();
        dto.cep = empresa.getCep();
        dto.bairro = empresa.getBairro();
        dto.municipio = empresa.getMunicipio();
        dto.uf = empresa.getUf();
        dto.email = empresa.getEmail();
        dto.telefone = empresa.getTelefone();
        dto.celular = empresa.getCelular();
        dto.status = empresa.isStatus();
        return dto;
    }

    public static List<EmpresaDto> toListEmpresaDto(List<Empresa> empresas) {
        List<EmpresaDto> dtos = empresas.stream().map((e) -> toEmpresaDto(e))
                .collect(Collectors.toList());
        return dtos;
    }


}
