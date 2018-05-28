package br.senai.sp.info.pweb.ianes.dao;

import java.util.List;

import br.senai.sp.info.pweb.ianes.models.ItemDePatrimonio;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;

public interface MovimentacaoDAO extends DAO<Movimentacao> {
	
	public List<Movimentacao> buscarPorItem(ItemDePatrimonio itemPatrimonio);

}
