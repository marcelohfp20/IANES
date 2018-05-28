package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.CategoriaPatrimonioDao;
import br.senai.sp.info.pweb.ianes.models.CategoriaPatrimonio;


@Repository
@Transactional
public class CategoriaPatrimonioJpa implements CategoriaPatrimonioDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void persistir(CategoriaPatrimonio obj) {
		session.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(CategoriaPatrimonio obj) {
		session.getCurrentSession().update(obj);
		
		
	}

	@Override
	public void deletar(CategoriaPatrimonio obj) {
		session.getCurrentSession().delete(obj);
		
	}

	@Override
	public CategoriaPatrimonio buscar(Long id) {
		
		String hql = "FROM CategoriaPatrimonio co WHERE co.id = :id";
		Query query = session.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<CategoriaPatrimonio> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
		

	@Override
	public List<CategoriaPatrimonio> buscarTodos() {
		
		String hql = "FROM CategoriaPatrimonio";
		Query query = session.getCurrentSession().createQuery(hql);
		return query.list();
	}

	
	@Override
	public CategoriaPatrimonio buscarPorNome(String nome) {
		String hql = "FROM CategoriaPatrimonio co WHERE co.nome = :nome";
		Query query = session.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<CategoriaPatrimonio> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

}
