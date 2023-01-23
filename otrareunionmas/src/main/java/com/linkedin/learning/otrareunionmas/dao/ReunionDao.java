package com.linkedin.learning.otrareunionmas.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.linkedin.learning.otrareunionmas.dominio.Persona;
import com.linkedin.learning.otrareunionmas.dominio.Reunion;

public class ReunionDao extends AbstractDao<Reunion> {

	public ReunionDao() {
		setClazz(Reunion.class);
	}
	
	public Reunion proximaReunion() {
		String queryString = "FROM " + Reunion.class.getName() + " WHERE fecha > now() order by fecha";
		Query query = getEntityManager().createQuery(queryString).setMaxResults(1);
		return (Reunion)query.getSingleResult();
	}
	
	public List<Reunion> reunionesTomorrow() {
		String queryString = "FROM " + Reunion.class.getName() + " WHERE fecha between ?1 and ?2";
		Query query = getEntityManager().createQuery(queryString);
		LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
		query.setParameter(1, tomorrow.atStartOfDay());
		query.setParameter(2, tomorrow.plus(1, ChronoUnit.DAYS).atStartOfDay());
		return query.getResultList();
	}
	
	public List<Reunion> reunionesParticipantes(String numEmpleado) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Reunion> criteriaQuery = cb.createQuery(Reunion.class);
		
		Root<Persona> fromPersona = criteriaQuery.from(Persona.class);
		criteriaQuery.where(cb.equal(fromPersona.get("numeroEmpleado"), numEmpleado));
		
		Join<Persona, Reunion> joinReunion = fromPersona.join("reuniones", JoinType.INNER);
		
		CriteriaQuery<Reunion> cq = criteriaQuery.multiselect(joinReunion);
		TypedQuery<Reunion> query = getEntityManager().createQuery(cq);
		return query.getResultList();
	}
}
