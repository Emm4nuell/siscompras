package br.com.siscompras.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime datacriacao;
    private boolean status;
    private String arquivo;
    private String preco;
    private String frete;
    private String quantidade;
    private String url;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
