package com.linkedin.learning.otrareunionmas;

import java.util.List;

import com.linkedin.learning.otrareunionmas.dao.SalaDao;
import com.linkedin.learning.otrareunionmas.dominio.Sala;

public class AppConsultas {

	public static void main(String[] args) {
		
		SalaDao salaDao = new SalaDao();
		
		List<Sala> salasPara4 = salaDao.findSalasCriteria(4);
		System.out.println("Salas para 4: " + salasPara4);
		
		List<Sala> salasPara3 = salaDao.findSalasCriteria(3);
		System.out.println("Salas para 3: " + salasPara3);
		
		List<Sala> salasAdecuadasPara3 = salaDao.findSalasAdecuadas(3);
		System.out.println("Salas adecuadas para 3: " + salasAdecuadasPara3);		
	}

}
