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
	
	public boolean modificarRegistro(Alumno a) {
		boolean resultado = false;
		//UPDATE alumnos SET nombre
		String sql = "UPDATE alumnos SET nombre='"+a.getNombre()+
				"', PrimerAp='"+a.getPrimerAp()+
				"', SegundoAp='"+a.getSegundoAp()+
				"', Edad = "+a.getEdad()+
                ", semestre = "+a.getSemestre()+
                ", Carrera = '"+a.getCarrera()+
                "'WHERE NumControl = '"+a.getNumControl()+"'";
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public ArrayList<Alumno> buscarAlumnos(String filtro){
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		//"SELECT * FROM alumnos"
		String sql = "SELECT * FROM alumnos";
		ResultSet rs = conexion.ejecutarConsulta(sql);
		
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
