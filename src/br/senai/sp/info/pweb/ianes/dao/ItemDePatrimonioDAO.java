package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.ItemDePatrimonio;

public interface ItemDePatrimonioDAO extends DAO<ItemDePatrimonio>{
	public ItemDePatrimonio buscaPorNome(String nome);

}
