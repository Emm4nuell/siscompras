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
    private String foto;
    private boolean status;

//    private MaterialDto materialDto;

    public static UsuarioDto toUsuarioDto(Usuario usuario){

        UsuarioDto dto = new UsuarioDto();

        dto.id = usuario.getId();
        dto.nome = usuario.getNome();
        dto.cpf = usuario.getCpf();
        dto.rg = usuario.getRg();
        dto.sexo = usuario.getSexo();
        dto.email = usuario.getEmail();
        dto.contato = usuario.getContato();
        dto.datanascimento = usuario.getDatanascimento();
        dto.datacadastro = usuario.getDatacadastro();
        dto.nivelacesso = usuario.getNivelacesso();
        dto.senha = usuario.getSenha();
        dto.foto = usuario.getFoto();
        dto.status = usuario.isStatus();

        return dto;
    }

    public static Usuario toUsuario(UsuarioDto dto){
        Usuario usuario = new Usuario();
                usuario.setId(dto.getId());
                usuario.setNome(dto.getNome());
                usuario.setCpf(dto.getCpf());
                usuario.setRg(dto.getRg());
                usuario.setSexo(dto.getSexo());
                usuario.setEmail(dto.getEmail());
                usuario.setContato(dto.getContato());
                usuario.setDatanascimento(dto.getDatanascimento());
                usuario.setDatacadastro(dto.getDatacadastro());
                usuario.setNivelacesso(dto.getNivelacesso());
                usuario.setSenha(dto.getSenha());
                usuario.setFoto(dto.getFoto());
                usuario.setStatus(dto.isStatus());

        return usuario;
    }

    public static List<UsuarioDto> toListUsuarioDto(List<Usuario> usuarios){
        List<UsuarioDto> dtos = usuarios.stream().map((e) -> toUsuarioDto(e))
                .collect(Collectors.toList());
        return dtos;
    }

}
