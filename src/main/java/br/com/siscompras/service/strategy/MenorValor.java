package br.com.siscompras.service.strategy;

import br.com.siscompras.entity.Cotacao;
import br.com.siscompras.service.CalcularPrecoStrategy;

import java.util.List;

public class MenorValor implements CalcularPrecoStrategy {

    private double total = 0.00;

    @Override
    public double CalcularPreco(List<Cotacao> cotacoes) {

        cotacoes.forEach(e -> {
            if(this.total == 0.00){
                this.total = e.getPrecototal();
            }else if(this.total > e.getPrecototal()){
                this.total = e.getPrecototal();
            }
        });

        return this.total;
    }
}
