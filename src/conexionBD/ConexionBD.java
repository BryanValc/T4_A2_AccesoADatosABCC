package conexionBD;

import java.sql.*;

public class ConexionBD {
	
	private Connection conexion;
	private Statement stm;
	private ResultSet rs;
	
	public ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/Escuela_Topicos";
			
			conexion = DriverManager.getConnection(URL,"root","c1s1g7o");
			
			System.out.println("Conexion establecida");
			
		} catch (ClassNotFoundException e) {
			System.out.printf("Error de Driver");
		} catch (SQLException e) {
			System.out.printf("Error de conexion a MySQL o de la BD");
		}
	}
	
	public void cerrarConnexion() {
		try {
			stm.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//		ABC
	public boolean ejecutarInstruccion(String sql) {
		try {
			stm = conexion.createStatement();
			return stm.executeUpdate(sql)==1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//		Consulta
	public ResultSet ejecutarConsulta(String sql) {
		try {
			stm = conexion.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//		
	
	public static void main(String[] args) {
		
		new ConexionBD();
		
	}
	
}//class
