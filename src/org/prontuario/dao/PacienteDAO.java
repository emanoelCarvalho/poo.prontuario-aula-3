package org.prontuario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.prontuario.db.IConnection;
import org.prontuario.exception.PacienteNaoEncontradoException;
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
	public IPaciente findById(Long id) throws PacienteNaoEncontradoException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM pacientes P WHERE P.idpacientes = ? and P.sexo = 'm';";
		String sqlF = "SELECT * FROM pacientes P WHERE P.idpacientes = ? and P.sexo = 'f';";

		ResultSet rs;
		IPaciente p = null;
		try (PreparedStatement pstm = conn.getConnection().prepareStatement(sql)) {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				p = new PacienteMasculino(id, nome, cpf);
			} else {
				try (PreparedStatement pstmF = conn.getConnection().prepareStatement(sqlF)) {
					pstmF.setLong(1, id);
					rs = pstmF.executeQuery();
					if (rs.next()) {
						String nome = rs.getString("nome");
						String cpf = rs.getString("cpf");
						p = new PacienteFeminino(id, nome, cpf);
						return p;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (p == null)
			throw new PacienteNaoEncontradoException("Paciente nao encontrado");

		return p;
	}

	@Override
	public void update(IPaciente e) {
		String sql = "UPDATE pacientes SET nome = ?, cpf = ?, sexo = ? WHERE idpacientes = ?;";

		try (PreparedStatement ptst = conn.getConnection().prepareStatement(sql)) {
			ptst.setString(1, e.getNome());
			ptst.setString(2, e.getCpf());

			// Definir o gênero com base na instância de IPaciente
			if (e instanceof PacienteMasculino) {
				ptst.setString(3, "m");
			} else if (e instanceof PacienteFeminino) {
				ptst.setString(3, "f");
			}

			ptst.setLong(4, e.getId());

			ptst.executeUpdate();
		} catch (SQLException e1) {
			// Logar a exceção usando um framework de logging
			e1.printStackTrace();
		}
	}

	@Override
	public void delete(IPaciente e) {
		String sql = "DELETE FROM pacientes WHERE idpacientes=?";

		try (PreparedStatement ptst = conn.getConnection().prepareStatement(sql)) {
			ptst.setLong(1, e.getId());
			ptst.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<IPaciente> findAll() {
		String sql = "SELECT * FROM pacientes;";
		List<IPaciente> pacientes = new ArrayList<>();

		try (PreparedStatement ptst = conn.getConnection().prepareStatement(sql); ResultSet rs = ptst.executeQuery()) {

			while (rs.next()) {
				Long id = rs.getLong("idpacientes");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String sexo = rs.getString("sexo");

				IPaciente paciente;
				if ("m".equals(sexo)) {
					paciente = new PacienteMasculino(id, nome, cpf);
				} else if ("f".equals(sexo)) {
					paciente = new PacienteFeminino(id, nome, cpf);
				} else {
					continue;
				}

				pacientes.add(paciente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pacientes;
	}

}
