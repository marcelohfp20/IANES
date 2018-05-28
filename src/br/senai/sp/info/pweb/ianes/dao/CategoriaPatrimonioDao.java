package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.CategoriaPatrimonio;

public interface CategoriaPatrimonioDao extends DAO<CategoriaPatrimonio> {
	public CategoriaPatrimonio buscarPorNome(String nome);
}
