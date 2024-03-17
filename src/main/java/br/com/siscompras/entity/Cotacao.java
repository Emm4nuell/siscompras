package br.com.siscompras.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private String descricao;
    private String vendedor;
    private String arquivo;
    private double preco;
    private double frete;
    private double precototal;
    private String quantidade;
    private String url;
    private String observacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
