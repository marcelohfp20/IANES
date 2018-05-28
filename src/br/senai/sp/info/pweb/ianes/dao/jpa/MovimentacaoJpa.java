package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.MovimentacaoDAO;
import br.senai.sp.info.pweb.ianes.models.ItemDePatrimonio;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;

@Repository
@Transactional
public class MovimentacaoJpa implements MovimentacaoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(Movimentacao obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Movimentacao obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Movimentacao obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public Movimentacao buscar(Long id) {
		String hql = "FROM Movimentacao m WHERE m.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Movimentacao> movimentacoes = query.list();
		if (!movimentacoes.isEmpty()) {
			return movimentacoes.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Movimentacao> buscarTodos() {
		String hql = "FROM Movimentacao m";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<Movimentacao> buscarPorItem(ItemDePatrimonio itemPatrimonio) {
		String hql = "FROM Movimentacao m WHERE m.itemPatrimonio = :itemPatrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("itemPatrimonio", itemPatrimonio);
		List<Movimentacao> movimentacoes = query.list();
		if (!movimentacoes.isEmpty()) {
			return movimentacoes;
		} else {
			return null;
		}
	}
}
