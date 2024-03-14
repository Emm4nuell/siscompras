package br.com.siscompras.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime datacriacao;
    private Date dataconclusao;
    @NotBlank(message = "Campo prioridade é obrigatório!")
    private String prioridade;
    @NotBlank(message = "Campo descrição é obrigatório!")
    private String descricao;
    private Long quantidade;
    private boolean status;
    private String andamento;
    private BigDecimal mediavalor;
    private BigDecimal minvalor;
    private BigDecimal maxvalor;
    private String observacao;
    private String arquivo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY)
    private List<Cotacao> cotacoes;
}
