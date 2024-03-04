package br.com.siscompras.dto;

import br.com.siscompras.entity.Empresa;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

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

    public static Empresa toEmpresa(EmpresaDto dto){
        Empresa empresa = new Empresa(
                dto.getId(),
                dto.getCnpj(),
                dto.getNome(),
                dto.getFantasia(),
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getCep(),
                dto.getBairro(),
                dto.getMunicipio(),
                dto.getUf(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getCelular(),
                dto.isStatus(),
                null
        );
        return empresa;
    }

    public static EmpresaDto toEmpresaDto(Empresa empresa){
        EmpresaDto dto = new EmpresaDto(
                empresa.getId(),
                empresa.getCnpj(),
                empresa.getNome(),
                empresa.getFantasia(),
                empresa.getLogradouro(),
                empresa.getNumero(),
                empresa.getComplemento(),
                empresa.getCep(),
                empresa.getBairro(),
                empresa.getMunicipio(),
                empresa.getUf(),
                empresa.getEmail(),
                empresa.getTelefone(),
                empresa.getCelular(),
                empresa.isStatus()
        );
        return dto;
    }


}
