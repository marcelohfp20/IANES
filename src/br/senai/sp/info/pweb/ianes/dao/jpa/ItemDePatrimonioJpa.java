package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.ItemDePatrimonioDAO;
import br.senai.sp.info.pweb.ianes.models.ItemDePatrimonio;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;

@Repository
@Transactional
public class ItemDePatrimonioJpa implements ItemDePatrimonioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void persistir(ItemDePatrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(ItemDePatrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
		
	}

	@Override
	public void deletar(ItemDePatrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);
		
	}

	@Override
	public ItemDePatrimonio buscar(Long id) {
		String hql = "FROM ItemDePatrimonio o WHERE o.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<ItemDePatrimonio> resultado = query.list();
		if(!resultado.isEmpty()) {
			return (ItemDePatrimonio) resultado.get(0);
		}else {
			return null;
		}
		
		
	}

	@Override
	public List<ItemDePatrimonio> buscarTodos() {
		
		String hql = "FROM ItemDePatrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public ItemDePatrimonio buscaPorNome(String nome) {
	
		String hql = "FROM ItemDePatrimonio co WHERE co.nome = :nome";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<ItemDePatrimonio> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
	

}
