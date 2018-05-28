package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.Usuario;

public interface UsuarioDao extends DAO<Usuario>{

	public Usuario buscarPorEmail(String email);
	public Usuario buscarPorEmailESenha(String email, String senha);

}
