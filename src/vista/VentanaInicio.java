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
	
	JComboBox<String> comboEdad, comboCarrera, comboSemestre;
	JButton agregar, borrar, cancelar;
	JTextField numControl, nombre, primerAp, segundoAp;
	
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
		
		metodoMagico(new JLabel("NUMERO DE CONTROL:"), panelAltas, 90, 87, 130, 20);
		metodoMagico(new JLabel("NOMBRES:"), panelAltas, 90, 110, 130, 20);
		metodoMagico(new JLabel("APELLIDO PATERNO: "), panelAltas, 90, 133, 130, 20);
		metodoMagico(new JLabel("APELLIDO MATERNO: "), panelAltas, 90, 156, 130, 20);
		metodoMagico(new JLabel("EDAD: "), panelAltas, 90, 179, 130, 20);
		metodoMagico(new JLabel("SEMESTRE: "), panelAltas, 90, 209, 130, 20);
		metodoMagico(new JLabel("CARRERA: "), panelAltas, 90, 227, 130, 20);
		
		numControl = new JTextField();
		nombre = new JTextField();
		primerAp = new JTextField();
		segundoAp = new JTextField();
		comboEdad = new JComboBox<String>();
		comboSemestre = new JComboBox<String>();
		comboCarrera = new JComboBox<String>();
		
		for (int i = 1; i <= 122; i++) {
			comboEdad.addItem(""+i);
		}
		for (int i = 1; i <= 10; i++) {
			comboSemestre.addItem(""+i);
		}
		comboCarrera.addItem("ISC");
		comboCarrera.addItem("IM");
		comboCarrera.addItem("ADMON");
		comboCarrera.addItem("IIA");
		comboCarrera.addItem("LEC");
		
		metodoMagico(numControl, panelAltas, 226, 90, 134, 17);
		metodoMagico(nombre, panelAltas, 172, 113, 187, 17);
		metodoMagico(primerAp, panelAltas, 216, 136, 144, 17);
		metodoMagico(segundoAp, panelAltas, 216, 159, 144, 17);
		metodoMagico(comboEdad, panelAltas, 216, 187, 40, 16);
		metodoMagico(comboSemestre, panelAltas, 216, 215, 144, 16);
		metodoMagico(comboCarrera, panelAltas, 216, 231, 144, 16);
		
		agregar = new JButton("AGREGAR");
		agregar.addActionListener(this);
		metodoMagico(agregar, panelAltas, 380, 96, 90, 18);
		
		borrar = new JButton("BORRAR");
		borrar.addActionListener(this);
		metodoMagico(borrar, panelAltas, 380, 155, 90, 18);
		
		cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(this);
		metodoMagico(cancelar, panelAltas, 375, 203, 100, 18);
		
		
		
		recordAltas.add(panelAltas);
		dp.add(recordAltas);
		dp.setBounds(0, 0, 567, 425);
		add(dp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==agregar) {
			Alumno a = new Alumno(numControl.getText(), nombre.getText(), primerAp.getText(), segundoAp.getText(),
					(byte)(comboEdad.getSelectedIndex()+1), (byte)(comboSemestre.getSelectedIndex()+1), comboCarrera.getSelectedItem().toString());
			
			AlumnoDAO aDAO = new AlumnoDAO();
			System.out.println(aDAO.insertarRegistro(a)?"EXITO":"Me cambio de carrera");
		}
		if (arg0.getSource()==borrar) {
			metodoQueRestableceTODO(numControl,nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);
		}
		if (arg0.getSource()==cancelar) {
			recordAltas.setVisible(false);
		}
		
	}
	
	public void metodoMagico(Component c, JPanel p, int x, int y,int width, int height) {
		p.add(c);
		c.setBounds(x, y, width, height);
	}
	
	public void metodoQueRestableceTODO(Component...componentesGraficos) {
		
		for (Component c: componentesGraficos) {
			if (c instanceof JComboBox) {
				((JComboBox<?>)c).setSelectedIndex(0);
			}else if (c instanceof JTextField) {
				((JTextField)c).setText("");
			}
		}
		
			
		
		
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
