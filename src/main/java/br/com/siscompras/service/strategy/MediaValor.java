package br.com.siscompras.service.strategy;

import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.service.CalcularPrecoStrategy;

import java.util.List;

public class MediaValor implements CalcularPrecoStrategy {

    private double total = 0.00;

    @Override
    public double CalcularPreco(List<Cotacao> cotacoes) {
        cotacoes.forEach(e -> {
          this.total += e.getPrecototal();
        });
        return this.total/cotacoes.size();
    }
}
