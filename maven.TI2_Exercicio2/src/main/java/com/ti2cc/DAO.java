package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;

	public DAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexao efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexao nao efetuada com o postgres -- Driver nao encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao nao efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public boolean inserirConta(Conta conta) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO conta (numero, dono, saldo, limite) " + "VALUES (" + conta.getNumero() + ", '"
					+ conta.getDono() + "', '" + conta.getSaldo() + "', '" + conta.getLimite() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarConta(Conta conta) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE conta SET dono = '" + conta.getDono() + "', saldo = '" + conta.getSaldo()
					+ "', limite = '" + conta.getLimite() + "'" + " WHERE numero = " + conta.getNumero();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirConta(int numero) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM conta WHERE numero = " + numero);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Conta[] getContas() {
		Conta[] contas = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM conta");
			if (rs.next()) {
				rs.last();
				contas = new Conta[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					contas[i] = new Conta(rs.getInt("numero"), rs.getString("dono"), rs.getDouble("saldo"), rs.getDouble("limite"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return contas;
	}

}