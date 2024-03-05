package br.com.siscompras.dto;

import br.com.siscompras.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    @NotBlank(message = "Campo nome é obrigatório!")
    private String nome;
    @CPF(message = "CPF inválido!")
    @NotBlank(message = "Campo CPF é obrigatório!")
    private String cpf;
    private String rg;
    private String sexo;
    @Email(message = "E-mail inválido!")
    @NotBlank(message = "Campo E-mail é obrigatório!")
    private String email;
    private String contato;
    private Date datanascimento;
    private LocalDateTime datacadastro;
    private String nivelacesso;
    private String senha;
    private boolean status;

    public static UsuarioDto toUsuarioDto(Usuario usuario){
        UsuarioDto dto = new UsuarioDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getRg(),
                usuario.getSexo(),
                usuario.getEmail(),
                usuario.getContato(),
                usuario.getDatanascimento(),
                usuario.getDatacadastro(),
                usuario.getNivelacesso(),
                usuario.getSenha(),
                usuario.isStatus()
        );

        return dto;
    }

    public static Usuario toUsuario(UsuarioDto dto){
        Usuario usuario = new Usuario(
                dto.getId(),
                dto.getNome(),
                dto.getCpf(),
                dto.getRg(),
                dto.getSexo(),
                dto.getEmail(),
                dto.getContato(),
                dto.getDatanascimento(),
                dto.getDatacadastro(),
                dto.getNivelacesso(),
                dto.getSenha(),
                dto.isStatus(),
                null
        );

        return usuario;
    }

    public static List<UsuarioDto> toListUsuarioDto(List<Usuario> usuarios){
        List<UsuarioDto> dtos = usuarios.stream().map((e) -> toUsuarioDto(e))
                .collect(Collectors.toList());
        return dtos;
    }

}
