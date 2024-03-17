package br.com.siscompras.service;

import br.com.siscompras.entity.Cotacao;

import java.util.List;

public interface CalcularPrecoStrategy {

    double CalcularPreco(List<Cotacao> cotacoes);
}
