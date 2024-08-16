package org.prontuario.app;

import org.prontuario.dao.EntityDAO;
import org.prontuario.dao.PacienteDAO;
import org.prontuario.db.ConexaoMySql;
import org.prontuario.model.IPaciente;
import org.prontuario.model.PacienteFeminino;
import org.prontuario.model.PacienteMasculino;
import org.prontuario.util.ConfigLoader;

public class Application {
	public static void main(String[] args) {
		EntityDAO<IPaciente> pDao = new PacienteDAO(new ConexaoMySql());

			pDao.save(new PacienteMasculino(0l, "Emanoel", "5555"));
		

		System.out.println(ConfigLoader.getInstance("config").getProperty("DB_NAME"));

	}
}
