package br.edu.utfpr.models;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Database {
	private static final String USUARIO = "mysql";
	private static final String SENHA = "mysql";
	private static final String URL = "jdbc:mysql://localhost:3306/gestorfin";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection instance;

	private Database() {
	}

	public static final Connection getConnection() {

		if (instance == null) {
			synchronized (Database.class) {
				if (instance == null) {
					try {
						openConnection();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE,
								"Erro ao obter conexão com banco de dados", 0);
					}

				}
			}
		}
		return instance;
	}

	private static void openConnection() {
		try {
			Class.forName(DRIVER);
			instance = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
		}catch (Exception e) {
		}
	}

	public static boolean insert(String sql){
		PreparedStatement stmt = null;
		try {
			Connection db = getConnection();
			stmt = (PreparedStatement) db.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
