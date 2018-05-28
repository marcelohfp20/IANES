package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.Ambiente;


public interface ambienteDAO extends DAO<Ambiente> {
	
	public Ambiente buscarPorNome(String nome);
}

