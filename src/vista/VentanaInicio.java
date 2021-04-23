package vista;

import java.awt.Color;
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
	
	JMenuBar menuBar;
	JMenu altas, bajas, cambios, consultas;
	JMenuItem menuItemAltas;
	JInternalFrame recordAltas;
	
	JButton agregar;
	JTextField numControl, nombre, primerAp, segundoAp, edad, semestre, carrera;
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(584,490);
		setLocationRelativeTo(null);
		setTitle("Formulario");
		setVisible(true);
		
		menuBar = new JMenuBar();
		altas = new JMenu("Altas");
			menuItemAltas= new JMenuItem("registrar");
			altas.add(menuItemAltas);
			menuItemAltas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					recordAltas.setVisible(true);
					
				}
			});	
		bajas = new JMenu("Bajas");
		cambios = new JMenu("Cambios");
		consultas = new JMenu("Consultas");
		menuBar.add(altas);
		menuBar.add(bajas);
		menuBar.add(cambios);
		menuBar.add(consultas);
		setJMenuBar(menuBar);
		
		JDesktopPane dp = new JDesktopPane();
		recordAltas = new JInternalFrame();
		recordAltas.getContentPane().setLayout(null);
		recordAltas.setDefaultCloseOperation(HIDE_ON_CLOSE);
		recordAltas.setSize(567,425);
		recordAltas.setTitle("Altas Alumnos");
		
		JPanel panelAltas = new JPanel();//Panel Altas
		panelAltas.setLayout(null);
		panelAltas.setBackground(Color.WHITE);
		panelAltas.setBounds(1, 1, 567, 425);
		
		metodoMagico(new JLabel("Número de control"), panelAltas, 20, 20, 130, 20);
		metodoMagico(new JLabel("Nombre"), panelAltas, 20, 40, 130, 20);
		metodoMagico(new JLabel("Primer apellido"), panelAltas, 20, 60, 130, 20);
		metodoMagico(new JLabel("Segundo apellido"), panelAltas, 20, 80, 130, 20);
		metodoMagico(new JLabel("Edad"), panelAltas, 20, 100, 130, 20);
		metodoMagico(new JLabel("Semestre"), panelAltas, 20, 120, 130, 20);
		metodoMagico(new JLabel("Carrera"), panelAltas, 20, 140, 130, 20);
		
		numControl = new JTextField();
		nombre = new JTextField();
		primerAp = new JTextField();
		segundoAp = new JTextField();
		edad = new JTextField();
		semestre = new JTextField();
		carrera = new JTextField();
		
		metodoMagico(numControl, panelAltas, 130, 20, 100, 20);
		metodoMagico(nombre, panelAltas, 130, 40, 100, 20);
		metodoMagico(primerAp, panelAltas, 130, 60, 100, 20);
		metodoMagico(segundoAp, panelAltas, 130, 80, 100, 20);
		metodoMagico(edad, panelAltas, 130, 100, 50, 20);
		metodoMagico(semestre, panelAltas, 130, 120, 50, 20);
		metodoMagico(carrera, panelAltas, 130, 140, 100, 20);
		
		agregar = new JButton("AGREGAR");
		agregar.addActionListener(this);
		metodoMagico(agregar, panelAltas, 130, 160, 100, 20);
		
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
		
		recordAltas.add(panelAltas);
		dp.add(recordAltas);
		dp.setBounds(0, 0, 567, 425);
		add(dp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==agregar) {
			Alumno a = new Alumno(numControl.getText(), nombre.getText(), primerAp.getText(), segundoAp.getText(),
					(byte)(Integer.parseInt(edad.getText())), (byte)(Integer.parseInt(semestre.getText())), carrera.getText());
			
			AlumnoDAO aDAO = new AlumnoDAO();
			System.out.println(aDAO.insertarRegistro(a)?"EXITO":"Me cambio de carrera");
		}
		
	}
	
	public void metodoMagico(Component c, JPanel p, int x, int y,int width, int height) {
		p.add(c);
		c.setBounds(x, y, width, height);
	}
	 
}

public class VentanaInicio {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Interfaz();
			}
		});
		
	}

}
