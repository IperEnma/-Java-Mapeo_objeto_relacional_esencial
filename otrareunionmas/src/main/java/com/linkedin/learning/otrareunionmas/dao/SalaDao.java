package com.linkedin.learning.otrareunionmas.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.linkedin.learning.otrareunionmas.dominio.Sala;

public class SalaDao extends AbstractDao<Sala> {
	
	public SalaDao() {
		setClazz(Sala.class);
	}
	
	public List<Sala> findSalas(int n) {
		String queryString = "FROM " + Sala.class.getName() + " WHERE capacidad >= ?1";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter(1, n);
		return query.getResultList();
	}
	
	public List<Sala> findSalasCriteria(int n) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Sala> criteriaQuery = cb.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);
		criteriaQuery.select(root).where(cb.ge(root.get("capacidad"), n));
		Query query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public List<Sala> findSalasAdecuadas(int n) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Sala> criteriaQuery = cb.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);
		
		Predicate capacidadMinima = cb.ge(root.get("capacidad"), n);
		Predicate capacidadMaxima = cb.le(root.get("capacidad"), n);
		Predicate rangoCapacidad = cb.and(capacidadMaxima, capacidadMinima);
	
		criteriaQuery.select(root).where(rangoCapacidad);
		Query query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}
}
