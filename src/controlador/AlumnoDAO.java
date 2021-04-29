package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Alumno;

//DAO - Data Access Object

public class AlumnoDAO {
	
	ConexionBD conexion;

	public AlumnoDAO() {
		conexion = new ConexionBD();
		//this.conexion = conexion;
	}
	
	// Metodos para Altas, Bajas, Cambios y Consultas
	
	public boolean insertarRegistro(Alumno a) {
		boolean resultado = false;
		
		String sql="INSERT INTO alumnos VALUES('"+a.getNumControl()+"','"+a.getNombre()+"','"+a.getPrimerAp()+"','"+a.getSegundoAp()+"',"+a.getEdad()+","+a.getSemestre()+",'"+a.getCarrera()+"')";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean eliminarRegistro(String nc) {
		boolean resultado = false;
		
		String sql="DELETE FROM alumnos WHERE NumControl = '"+nc+"'";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(Alumno a, boolean flags[]) {
		boolean resultado = false;
		int primero=0;
		//UPDATE alumnos SET nombre
		
		String sql = "UPDATE alumnos SET ";
		
		if (flags[0]) {
			if (primero!=0) {
				sql+=", ";
			}
			primero+=1;
			sql+=("Nombre='"+a.getNombre()+"'");
		}
		if (flags[1]) {
			if (primero!=0) {
				sql+=", ";
			}
			primero+=1;
			sql+=("PrimerAp='"+a.getPrimerAp()+"'");
		}
		if (flags[2]) {
			if (primero!=0) {
				sql+=", ";
			}
			primero+=1;
			sql+=("SegundoAp='"+a.getSegundoAp()+"'");
		}
		if (flags[3]) {
			if (primero!=0) {
				sql+=", ";
			}
			primero+=1;
			sql+=("Edad="+a.getEdad());
		}
		if (flags[4]) {
			if (primero!=0) {
				sql+=", ";
			}
			primero+=1;
			sql+=("Semestre="+a.getSemestre());
		}
		if (flags[5]) {
			if (primero!=0) {
				sql+=", ";
			}
			primero+=1;
			sql+=("Carrera='"+a.getCarrera()+"'");
		}
		sql+=("WHERE NumControl = '"+a.getNumControl()+"'");
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public ArrayList<Alumno> buscarAlumnos(String filtro){
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		ResultSet rs;
		
		rs = conexion.ejecutarConsulta(filtro);
		//SELECT * FROM Alumnos WHERE prod_name = 'Microsoft 10-20 Keyboard' AND prod_price < 30;
		try {
			if (rs.next()) {
				do {
					listaAlumnos.add(new Alumno(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getByte(5),
							rs.getByte(6),
							rs.getString(7)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAlumnos;
	}
	
	
}
