package br.com.siscompras.repository;

import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
}
