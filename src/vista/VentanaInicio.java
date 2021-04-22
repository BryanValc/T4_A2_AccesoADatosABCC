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
		
		metodoMagico(new JLabel("Número de control"), 20, 20, 130, 20);
		metodoMagico(new JLabel("Nombre"), 20, 40, 130, 20);
		metodoMagico(new JLabel("Primer apellido"), 20, 60, 130, 20);
		metodoMagico(new JLabel("Segundo apellido"), 20, 80, 130, 20);
		metodoMagico(new JLabel("Edad"), 20, 100, 130, 20);
		metodoMagico(new JLabel("Semestre"), 20, 120, 130, 20);
		metodoMagico(new JLabel("Carrera"), 20, 140, 130, 20);
		
		numControl = new JTextField();
		nombre = new JTextField();
		primerAp = new JTextField();
		segundoAp = new JTextField();
		edad = new JTextField();
		semestre = new JTextField();
		carrera = new JTextField();
		
		metodoMagico(numControl, 130, 20, 100, 20);
		metodoMagico(nombre, 130, 40, 100, 20);
		metodoMagico(primerAp, 130, 60, 100, 20);
		metodoMagico(segundoAp, 130, 80, 100, 20);
		metodoMagico(edad, 130, 100, 50, 20);
		metodoMagico(semestre, 130, 120, 50, 20);
		metodoMagico(carrera, 130, 140, 100, 20);
		
		enviar = new JButton("Enviar");
		enviar.addActionListener(this);
		metodoMagico(enviar, 130, 160, 100, 20);
		
		edad.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				String value = edad.getText();
				int code=ke.getKeyCode();
				if ((ke.getKeyChar()>='0' && ke.getKeyChar()<='9')|| (code==KeyEvent.VK_BACK_SPACE)) {
					edad.setEditable(true);
				}else{
					edad.setEditable(false);
				}
			}
		});
		
		semestre.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				String value = semestre.getText();
				int code=ke.getKeyCode();
				if ((ke.getKeyChar()>='0' && ke.getKeyChar()<='9')|| (code==KeyEvent.VK_BACK_SPACE)) {
					semestre.setEditable(true);
				}else{
					semestre.setEditable(false);
				}
			}
		});
		
		
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
