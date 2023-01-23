package com.linkedin.learning.otrareunionmas.dao;

import com.linkedin.learning.otrareunionmas.dominio.Persona;

public class PersonaDao extends AbstractDao<Persona> {
	
	public PersonaDao() {
		setClazz(Persona.class);
	}
}
