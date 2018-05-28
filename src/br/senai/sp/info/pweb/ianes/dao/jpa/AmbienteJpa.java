package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.ambienteDAO;
import br.senai.sp.info.pweb.ianes.models.Ambiente;


@Repository
@Transactional
public class AmbienteJpa implements ambienteDAO {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void persistir(Ambiente obj) {
		session.getCurrentSession().persist(obj);
		
		
	}

	@Override
	public void alterar(Ambiente obj) {
		session.getCurrentSession().update(obj);
		
		
	}

	@Override
	public void deletar(Ambiente obj) {
		session.getCurrentSession().delete(obj);
		
		
	}

	@Override
	public Ambiente buscar(Long id) {
		String hql = "FROM Ambiente am WHERE am.id = :id";
		Query query = session.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Ambiente> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}


	@Override
	public List<Ambiente> buscarTodos() {
		String hql = "FROM Ambiente";
		Query query = session.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public Ambiente buscarPorNome(String nome) {
		String hql = "FROM Ambiente am WHERE am.nome = :nome";
		Query query = session.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<Ambiente> result = query.list();
		if(!result.isEmpty()) {
			return result.get(0);
		}else {
		return null;
	}
	}

}
