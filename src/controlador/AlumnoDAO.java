package controlador;

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
	
	
	
	
	
	
}
