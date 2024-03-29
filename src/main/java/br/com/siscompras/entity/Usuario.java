package br.com.siscompras.entity;

import br.com.siscompras.enums.PerfilEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String rg;
    private String sexo;
    @Column(unique = true)
    private String email;
    private String contato;
    private Date datanascimento;
    private LocalDateTime datacadastro;
    @ElementCollection(fetch = FetchType.LAZY) // É usado para mapear uma coleção
    @CollectionTable(name = "PERFIS")
    @Enumerated(EnumType.ORDINAL)
    private Set<PerfilEnum> perfilEnums = new HashSet<>();
    private String senha;
    private String foto;
    private boolean status;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Material> materiais;


}
