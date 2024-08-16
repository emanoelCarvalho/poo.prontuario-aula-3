package org.prontuario.app;

import java.util.List;

import org.prontuario.dao.EntityDAO;
import org.prontuario.dao.PacienteDAO;
import org.prontuario.db.ConexaoMySql;
import org.prontuario.model.IPaciente;
import org.prontuario.model.PacienteMasculino;

public class Application {
	public static void main(String[] args) {
		EntityDAO<IPaciente> pDao = new PacienteDAO(new ConexaoMySql());
		List<IPaciente> p;

		pDao.save(new PacienteMasculino(0l, "lolo", "88888888"));
		System.out.println(p = pDao.findAll());

	}
}
