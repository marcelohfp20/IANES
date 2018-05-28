package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.api.Session;

import br.senai.sp.info.pweb.ianes.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.ianes.models.CategoriaPatrimonio;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;



@Repository
@Transactional
public class PatrimonioJpa implements PatrimonioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void persistir(Patrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);
		
	}

	@Override
	public void alterar(Patrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Patrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public Patrimonio buscar(Long id) {
		String hql = "FROM Patrimonio o WHERE o.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Patrimonio> resultado = query.list();
		if(!resultado.isEmpty()) {
			return (Patrimonio) resultado.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Patrimonio> buscarTodos() {
		
		String hql = "FROM Patrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Patrimonio buscarPorNome(String nome) {
		String hql = "FROM Patrimonio co WHERE co.nome = :nome";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<Patrimonio> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
}
