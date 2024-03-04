package br.com.siscompras.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

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
    private String prioridade;
    private String descricao;
    private Long quantidade;
    private boolean status;
    private String andamento;
    private String mediavalor;
    private String minvalor;
    private String maxvalor;
    private String observacao;
    private String arquivo;
}
