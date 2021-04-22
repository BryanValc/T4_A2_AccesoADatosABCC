package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.AllPermission;

import javax.swing.*;

import controlador.AlumnoDAO;
import modelo.Alumno;

class Interfaz extends JFrame implements ActionListener{
	
	JButton enviar;
	JTextField numControl, nombre, primerAp, segundoAp, edad, semestre, carrera;
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(280,240);
		setLocationRelativeTo(null);
		setTitle("Formulario");
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void metodoMagico(Component c,int x, int y,int width, int height) {
		add(c);
		c.setBounds(x, y, width, height);
	}
	 
}

public class VentanaInicio {
	
	public static void main(String[] args) {
		
		//Suponiendo que los datos vienen desde la interfaz grafica
		
		/*Alumno a = new Alumno("01", "Luke", "Skywalker", "-", (byte)50, (byte)10, "ISC");
		
		AlumnoDAO aDAO = new AlumnoDAO();
		
		System.out.println(aDAO.insertarRegistro(a)?"EXITO":"Me cambio de carrera");*/
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Interfaz();
			}
		});
		
	}

}
