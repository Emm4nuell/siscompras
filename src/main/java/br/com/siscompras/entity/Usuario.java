package br.com.siscompras.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

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
    private String nivelacesso;
    private String senha;
    private boolean status;


}
