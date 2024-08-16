package org.prontuario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.prontuario.db.IConnection;
import org.prontuario.model.IPaciente;
import org.prontuario.model.PacienteFeminino;
import org.prontuario.model.PacienteMasculino;

public class PacienteDAO implements EntityDAO<IPaciente> {

	private IConnection conn;

	public PacienteDAO(IConnection c) {
		this.conn = c;
	}

	@Override
	public void save(IPaciente e) {
		String sql = "INSERT INTO pacientes VALUES (?,?,?, ?);";

		if (e instanceof PacienteMasculino) {
			try (PreparedStatement ptst = conn.getConnection().prepareStatement(sql)) {
				ptst.setLong(1, e.getId());
				ptst.setString(2, e.getNome());
				ptst.setString(3, e.getCpf());
				ptst.setString(4, "m");
				ptst.execute();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		} else if (e instanceof PacienteFeminino) {
			try (PreparedStatement ptst = conn.getConnection().prepareStatement(sql)) {
				ptst.setLong(1, e.getId());
				ptst.setString(2, e.getNome());
				ptst.setString(3, e.getCpf());
				ptst.setString(4, "f");
				ptst.execute();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}

	}

	@Override
	public IPaciente findById(Long id) {
	    String sql = "SELECT id, nome, cpf, genero FROM pacientes WHERE id = ?;";
	    IPaciente paciente = null;

	    try (PreparedStatement ptst = conn.getConnection().prepareStatement(sql)) {
	        ptst.setLong(1, id);
	        ResultSet rs = ptst.executeQuery();

	        if (rs.next()) {
	            Long pacienteId = rs.getLong("id");
	            String nome = rs.getString("nome");
	            String cpf = rs.getString("cpf");
	            String sexo = rs.getString("sexo");

	            if ("m".equals(sexo)) {
	                paciente = new PacienteMasculino(pacienteId, nome, cpf);
	            } else if ("f".equals(sexo)) {
	                paciente = new PacienteFeminino(pacienteId, nome, cpf);
	            }
	        }
	    } catch (SQLException e) {
	        // Log the exception using a logging framework
	        e.printStackTrace();
	    }

	    return paciente;
	}


	@Override
	public void update(IPaciente e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(IPaciente e) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IPaciente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
