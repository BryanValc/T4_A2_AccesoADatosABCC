package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.AllPermission;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;

import controlador.AlumnoDAO;
import modelo.Alumno;

class Interfaz extends JFrame implements ActionListener{
	
	JMenuBar menuBar;
	JMenu altas, bajas, cambios, consultas;
	JMenuItem menuItemAltas, menuItemBajas, menuItemCambios, menuItemConsultas;
	JInternalFrame recordAltas, recordBajas, recordCambios, recordConsultas, lista;
	
	JLabel lblNumeroDeControl,lblNombres,lblApellidoPaterno,lblApellidoMaterno,lblEdad,lblSemestre,lblCarrera;
	JComboBox<String> comboEdad, comboCarrera, comboSemestre;
	JButton interaccion, borrar, cancelar, busqueda;
	JTextField numControl, nombre, primerAp, segundoAp;
	JCheckBox cbTodos,cbNumeroDeControl,cbNombres,cbApellidoPaterno,cbApellidoMaterno,cbEdad,cbSemestre,cbCarrera;
	Icon iconoBusqueda = new ImageIcon("./archivos/iconoBusqueda.PNG");//imagen
	
	JTable tabla;
	JScrollPane sp = new JScrollPane();
	
	/*JTable table=new JTable();
	JTableHeader header = table.getTableHeader();
	*/
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(584,490);
		setLocationRelativeTo(null);
		setTitle("Formulario");
		setVisible(true);
		
		numControl = new JTextField();//=====================================================Reasignacion=======================================
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
		
		metodoQueRestableceTODO(comboEdad,comboSemestre,comboCarrera);
		
		lblNumeroDeControl=new JLabel("NUMERO DE CONTROL:");
		lblNombres=new JLabel("NOMBRE:");
		lblApellidoPaterno=new JLabel("APELLIDO PATERNO:");
		lblApellidoMaterno=new JLabel("APELLIDO MATERNO:");
		lblEdad=new JLabel("EDAD:");
		lblSemestre=new JLabel("SEMESTRE:");
		lblCarrera=new JLabel("CARRERA:");
		
		borrar = new JButton("BORRAR");
		borrar.addActionListener(this);
		interaccion = new JButton("");
		interaccion.addActionListener(this);
		cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(this);
		busqueda = new JButton(iconoBusqueda);
		busqueda.addActionListener(this);
		
		cbTodos=new JCheckBox();//==============================================================Checkboxes=================================================
		cbTodos.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				if(cbTodos.isSelected()) {
					cbNumeroDeControl.setSelected(false);
					cbNombres.setSelected(false);
					cbApellidoPaterno.setSelected(false);
					cbApellidoMaterno.setSelected(false);
					cbEdad.setSelected(false);
					cbSemestre.setSelected(false);
					cbCarrera.setSelected(false);
					
					cbNumeroDeControl.setEnabled(false);
					cbNombres.setEnabled(false);
					cbApellidoPaterno.setEnabled(false);
					cbApellidoMaterno.setEnabled(false);
					cbEdad.setEnabled(false);
					cbSemestre.setEnabled(false);
					cbCarrera.setEnabled(false);
					
					numControl.setEditable(true);
					nombre.setEditable(true);
					primerAp.setEditable(true);
					segundoAp.setEditable(true);
					comboEdad.setEnabled(true);
					comboSemestre.setEnabled(true);
					comboCarrera.setEnabled(true);
	            }else {
	            	numControl.setEditable(false);
					nombre.setEditable(false);
					primerAp.setEditable(false);
					segundoAp.setEditable(false);
					comboEdad.setEnabled(false);
					comboSemestre.setEnabled(false);
					comboCarrera.setEnabled(false);
					
					cbNumeroDeControl.setEnabled(true);
					cbNombres.setEnabled(true);
					cbApellidoPaterno.setEnabled(true);
					cbApellidoMaterno.setEnabled(true);
					cbEdad.setEnabled(true);
					cbSemestre.setEnabled(true);
					cbCarrera.setEnabled(true);
	            }
			}
	    });
		cbNumeroDeControl=new JCheckBox();
		cbNumeroDeControl.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbNumeroDeControl.isSelected()) {
					numControl.setEditable(true);
	            }else {
	            	numControl.setText("");
	            	numControl.setEditable(false);
	            }
			}
	    });
		cbNombres=new JCheckBox();
		cbNombres.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbNombres.isSelected()) {
					nombre.setEditable(true);
	            }else {
	            	nombre.setText("");
	            	nombre.setEditable(false);
	            }
			}
	    });
		cbApellidoPaterno=new JCheckBox();
		cbApellidoPaterno.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbApellidoPaterno.isSelected()) {
					primerAp.setEditable(true);
	            }else {
	            	primerAp.setText("");
	            	primerAp.setEditable(false);
	            }
			}
	    });
		cbApellidoMaterno=new JCheckBox();
		cbApellidoMaterno.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbApellidoMaterno.isSelected()) {
					segundoAp.setEditable(true);
	            }else {
	            	segundoAp.setText("");
	            	segundoAp.setEditable(false);
	            }
			}
	    });
		cbEdad=new JCheckBox();
		cbEdad.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbEdad.isSelected()) {
					comboEdad.setEnabled(true);
	            }else {
	            	comboEdad.setSelectedIndex(-1);
	            	comboEdad.setEnabled(false);
	            }
			}
	    });
		cbSemestre=new JCheckBox();
		cbSemestre.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbSemestre.isSelected()) {
					comboSemestre.setEnabled(true);
	            }else {
	            	comboSemestre.setSelectedIndex(-1);
	            	comboSemestre.setEnabled(false);
	            }
			}
	    });
		cbCarrera=new JCheckBox();
		cbCarrera.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbCarrera.isSelected()) {
					comboCarrera.setEnabled(true);
	            }else {
	            	comboCarrera.setSelectedIndex(-1);
	            	comboCarrera.setEnabled(false);
	            }
			}
	    });
		
		
		JDesktopPane dp = new JDesktopPane();
		
		
		recordAltas = new JInternalFrame();//==============================================Frame Altas====================================================================
			recordAltas.getContentPane().setLayout(null);
			recordAltas.setDefaultCloseOperation(HIDE_ON_CLOSE);
			recordAltas.setSize(567,290);
			recordAltas.setTitle("Altas Alumnos");
		JPanel panelAltasTitulo = new JPanel();//Panel titulo Altas
			panelAltasTitulo.setLayout(null);
			panelAltasTitulo.setBackground(Color.GREEN);
			panelAltasTitulo.setBounds(0, 0, 567, 50);
		JLabel tituloAltas = new JLabel("ALTAS ALUMNOS");
			tituloAltas.setFont(new Font("Calibri", Font.BOLD, 20));
			tituloAltas.setForeground(Color.WHITE);
			metodoMagico(tituloAltas, 30, 20, 220, 20, panelAltasTitulo);
		JPanel panelAltas = new JPanel();//Panel Altas
			panelAltas.setLayout(null);
			panelAltas.setBackground(Color.WHITE);
			panelAltas.setBounds(0, 50, 567, 425);
		
		recordBajas = new JInternalFrame();//==============================================Frame Bajas========================================================================
			recordBajas.getContentPane().setLayout(null);
			recordBajas.setDefaultCloseOperation(HIDE_ON_CLOSE);
			recordBajas.setSize(567,290);
			recordBajas.setTitle("Bajas Alumnos");
		JPanel panelBajasTitulo = new JPanel();//Panel titulo Bajas
			panelBajasTitulo.setLayout(null);
			panelBajasTitulo.setBackground(Color.RED);
			panelBajasTitulo.setBounds(0, 0, 567, 50);
		JLabel tituloBajas = new JLabel("BAJAS ALUMNOS");
			tituloBajas.setFont(new Font("Calibri", Font.BOLD, 20));
			tituloBajas.setForeground(Color.WHITE);
			metodoMagico(tituloBajas, 30, 20, 220, 20, panelBajasTitulo);
		JPanel panelBajas = new JPanel();//Panel Bajas
			panelBajas.setLayout(null);
			panelBajas.setBackground(Color.WHITE);
			panelBajas.setBounds(0, 50, 567, 425);
			
		recordCambios = new JInternalFrame();//=========================================Frame Cambios======================================================================
			recordCambios.getContentPane().setLayout(null);
			recordCambios.setDefaultCloseOperation(HIDE_ON_CLOSE);
			recordCambios.setSize(567,290);
			recordCambios.setTitle("Modificaciones Alumnos");
		JPanel panelCambiosTitulo = new JPanel();//Panel titulo Cambios
			panelCambiosTitulo.setLayout(null);
			panelCambiosTitulo.setBackground(Color.ORANGE);
			panelCambiosTitulo.setBounds(0, 0, 567, 50);
		JLabel tituloCambios = new JLabel("MODIFICACIONES ALUMNOS");
			tituloCambios.setFont(new Font("Calibri", Font.BOLD, 20));
			tituloCambios.setForeground(Color.WHITE);
			metodoMagico(tituloCambios, 30, 20, 270, 20, panelCambiosTitulo);
		JPanel panelCambios = new JPanel();//Panel Cambios
			panelCambios.setLayout(null);
			panelCambios.setBackground(Color.WHITE);
			panelCambios.setBounds(0, 50, 567, 425);
		
		recordConsultas = new JInternalFrame();//========================================Frame Consultas==================================================================
			recordConsultas.getContentPane().setLayout(null);
			recordConsultas.setDefaultCloseOperation(HIDE_ON_CLOSE);
			recordConsultas.setSize(567,290);
			recordConsultas.setTitle("Bajas Alumnos");
		JPanel panelConsultasTitulo = new JPanel();//Panel titulo Consultas
			panelConsultasTitulo.setLayout(null);
			panelConsultasTitulo.setBackground(Color.BLUE);
			panelConsultasTitulo.setBounds(0, 0, 567, 50);
		JLabel tituloConsultas = new JLabel("CONSULTAS ALUMNOS");
			tituloConsultas.setFont(new Font("Calibri", Font.BOLD, 20));
			tituloConsultas.setForeground(Color.WHITE);
			metodoMagico(tituloConsultas, 30, 20, 270, 20, panelConsultasTitulo);
		JPanel panelConsultas = new JPanel();//Panel Consultas
			panelConsultas.setLayout(null);
			panelConsultas.setBackground(Color.WHITE);
			panelConsultas.setBounds(0, 50, 567, 425);
			
		lista = new JInternalFrame();//========================================Frame Lista==================================================================
			lista.getContentPane().setLayout(null);
			lista.setDefaultCloseOperation(HIDE_ON_CLOSE);
			lista.setSize(567,137);
			lista.setLocation(0, 290);
			lista.setTitle("Lista");
		
		menuBar = new JMenuBar();
		altas = new JMenu("Altas");
			menuItemAltas= new JMenuItem("registrar");
			altas.add(menuItemAltas);
			menuItemAltas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");
					
					recordAltas.setVisible(true);
					recordBajas.setVisible(false);
					recordCambios.setVisible(false);
					recordConsultas.setVisible(false);
					
					numControl.setEditable(true);
					nombre.setEditable(true);
					primerAp.setEditable(true);
					segundoAp.setEditable(true);
					comboEdad.setEnabled(true);
					comboSemestre.setEnabled(true);
					comboCarrera.setEnabled(true);
					
					metodoMagico(lblNumeroDeControl, 90, 37, 130, 20, panelAltas);//Labels
					metodoMagico(lblNombres, 90, 60, 130, 20, panelAltas);
					metodoMagico(lblApellidoPaterno, 90, 83, 130, 20, panelAltas);
					metodoMagico(lblApellidoMaterno, 90, 106, 130, 20, panelAltas);
					metodoMagico(lblEdad, 90, 129, 130, 20, panelAltas);
					metodoMagico(lblSemestre, 90, 159, 130, 20, panelAltas);
					metodoMagico(lblCarrera, 90, 177, 130, 20, panelAltas);
					
					metodoMagico(numControl, 226, 40, 134, 17, panelAltas);//Posicionamiento
					metodoMagico(nombre, 172, 63, 187, 17, panelAltas);
					metodoMagico(primerAp, 216, 86, 144, 17, panelAltas);
					metodoMagico(segundoAp, 216, 109, 144, 17, panelAltas);
					metodoMagico(comboEdad, 216, 137, 40, 16, panelAltas);
					metodoMagico(comboSemestre, 216, 165, 144, 16, panelAltas);
					metodoMagico(comboCarrera, 216, 181, 144, 16, panelAltas);
					
					metodoMagico(borrar, 380, 105, 90, 18, panelAltas);
					interaccion.setText("AGREGAR");
					metodoMagico(interaccion, 380, 46, 90, 18, panelAltas);
					metodoMagico(cancelar, 375, 153, 100, 18, panelAltas);
					
					
				}
			});	
		bajas = new JMenu("Bajas");
			menuItemBajas= new JMenuItem("eliminar");
			bajas.add(menuItemBajas);
			menuItemBajas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");
					
					recordAltas.setVisible(false);
					recordBajas.setVisible(true);
					recordCambios.setVisible(false);
					recordConsultas.setVisible(false);
					
					metodoQueRestableceTODO(nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);
					
					numControl.setEditable(true);
					nombre.setEditable(false);
					primerAp.setEditable(false);
					segundoAp.setEditable(false);
					comboEdad.setEnabled(false);
					comboSemestre.setEnabled(false);
					comboCarrera.setEnabled(false);
					
					metodoMagico(lblNumeroDeControl, 90, 37, 130, 20, panelBajas);//Labels
					metodoMagico(lblNombres, 90, 60, 130, 20, panelBajas);
					metodoMagico(lblApellidoPaterno, 90, 83, 130, 20, panelBajas);
					metodoMagico(lblApellidoMaterno, 90, 106, 130, 20, panelBajas);
					metodoMagico(lblEdad, 90, 129, 130, 20, panelBajas);
					metodoMagico(lblSemestre, 90, 159, 130, 20, panelBajas);
					metodoMagico(lblCarrera, 90, 177, 130, 20, panelBajas);
					
					metodoMagico(numControl, 226, 40, 134, 17, panelBajas);//Posicionamiento
					metodoMagico(nombre, 172, 63, 187, 17, panelBajas);
					metodoMagico(primerAp, 216, 86, 144, 17, panelBajas);
					metodoMagico(segundoAp, 216, 109, 144, 17, panelBajas);
					metodoMagico(comboEdad, 216, 137, 40, 16, panelBajas);
					metodoMagico(comboSemestre, 216, 165, 144, 16, panelBajas);
					metodoMagico(comboCarrera, 216, 181, 144, 16, panelBajas);
					
					metodoMagico(busqueda, 380, 11, 84, 30, panelBajas);
					metodoMagico(borrar, 380, 46, 90, 18, panelBajas);
					interaccion.setText("ELIMINAR");
					metodoMagico(interaccion, 380, 105, 90, 18, panelBajas);
					metodoMagico(cancelar, 375, 153, 100, 18, panelBajas);
				}
			});	
		cambios = new JMenu("Cambios");
			menuItemCambios= new JMenuItem("modificar");
			cambios.add(menuItemCambios);
			menuItemCambios.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");
					
					recordAltas.setVisible(false);
					recordBajas.setVisible(false);
					recordCambios.setVisible(true);
					recordConsultas.setVisible(false);
					
					numControl.setEditable(true);
					nombre.setEditable(true);
					primerAp.setEditable(true);
					segundoAp.setEditable(true);
					comboEdad.setEnabled(true);
					comboSemestre.setEnabled(true);
					comboCarrera.setEnabled(true);
					
					metodoMagico(lblNumeroDeControl, 90, 37, 130, 20, panelCambios);//Labels
					metodoMagico(lblNombres, 90, 60, 130, 20, panelCambios);
					metodoMagico(lblApellidoPaterno, 90, 83, 130, 20, panelCambios);
					metodoMagico(lblApellidoMaterno, 90, 106, 130, 20, panelCambios);
					metodoMagico(lblEdad, 90, 129, 130, 20, panelCambios);
					metodoMagico(lblSemestre, 90, 159, 130, 20, panelCambios);
					metodoMagico(lblCarrera, 90, 177, 130, 20, panelCambios);
					
					metodoMagico(numControl, 226, 40, 134, 17, panelCambios);//Posicionamiento
					metodoMagico(nombre, 172, 63, 187, 17, panelCambios);
					metodoMagico(primerAp, 216, 86, 144, 17, panelCambios);
					metodoMagico(segundoAp, 216, 109, 144, 17, panelCambios);
					metodoMagico(comboEdad, 216, 137, 40, 16, panelCambios);
					metodoMagico(comboSemestre, 216, 165, 144, 16, panelCambios);
					metodoMagico(comboCarrera, 216, 181, 144, 16, panelCambios);
					
					metodoMagico(busqueda, 380, 11, 84, 30, panelCambios);
					metodoMagico(borrar, 380, 46, 90, 18, panelCambios);
					interaccion.setText("GUARDAR CAMBIOS");
					metodoMagico(interaccion, 380, 105, 160, 18, panelCambios);
					metodoMagico(cancelar, 375, 153, 165, 18, panelCambios);
				}
			});
		consultas = new JMenu("Consultas");
			menuItemConsultas= new JMenuItem("buscar");
			consultas.add(menuItemConsultas);
			menuItemConsultas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");
					
					recordAltas.setVisible(false);
					recordBajas.setVisible(false);
					recordCambios.setVisible(false);
					recordConsultas.setVisible(true);
					
					metodoQueRestableceTODO(numControl,nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);
					
					numControl.setEditable(false);
					nombre.setEditable(false);
					primerAp.setEditable(false);
					segundoAp.setEditable(false);
					comboEdad.setEnabled(false);
					comboSemestre.setEnabled(false);
					comboCarrera.setEnabled(false);
					
					cbTodos.setSelected(false);
					cbNumeroDeControl.setSelected(false);
					cbNombres.setSelected(false);
					cbApellidoPaterno.setSelected(false);
					cbApellidoMaterno.setSelected(false);
					cbEdad.setSelected(false);
					cbSemestre.setSelected(false);
					cbCarrera.setSelected(false);
					
					metodoMagico(cbTodos, 70, 17, 20, 20, panelConsultas);
					metodoMagico(cbNumeroDeControl, 70, 37, 20, 20, panelConsultas);//Checkboxes
					metodoMagico(cbNombres, 70, 60, 20, 20, panelConsultas);
					metodoMagico(cbApellidoPaterno, 70, 83, 20, 20, panelConsultas);
					metodoMagico(cbApellidoMaterno, 70, 106, 20, 20, panelConsultas);
					metodoMagico(cbEdad, 70, 129, 20, 20, panelConsultas);
					metodoMagico(cbSemestre, 70, 159, 20, 20, panelConsultas);
					metodoMagico(cbCarrera, 70, 177, 20, 20, panelConsultas);
					
					metodoMagico(lblNumeroDeControl, 90, 37, 130, 20, panelConsultas);//Labels
					metodoMagico(lblNombres, 90, 60, 130, 20, panelConsultas);
					metodoMagico(lblApellidoPaterno, 90, 83, 130, 20, panelConsultas);
					metodoMagico(lblApellidoMaterno, 90, 106, 130, 20, panelConsultas);
					metodoMagico(lblEdad, 90, 129, 130, 20, panelConsultas);
					metodoMagico(lblSemestre, 90, 159, 130, 20, panelConsultas);
					metodoMagico(lblCarrera, 90, 177, 130, 20, panelConsultas);
					
					metodoMagico(numControl, 226, 40, 134, 17, panelConsultas);//Posicionamiento
					metodoMagico(nombre, 172, 63, 187, 17, panelConsultas);
					metodoMagico(primerAp, 216, 86, 144, 17, panelConsultas);
					metodoMagico(segundoAp, 216, 109, 144, 17, panelConsultas);
					metodoMagico(comboEdad, 216, 137, 40, 16, panelConsultas);
					metodoMagico(comboSemestre, 216, 165, 144, 16, panelConsultas);
					metodoMagico(comboCarrera, 216, 181, 144, 16, panelConsultas);
					
					metodoMagico(busqueda, 380, 11, 84, 30, panelConsultas);
					metodoMagico(borrar, 380, 46, 90, 18, panelConsultas);
					//interaccion.setText("GUARDAR CAMBIOS");
					//metodoMagico(interaccion, 380, 105, 110, 18, panelBajas);
					metodoMagico(cancelar, 375, 153, 100, 18, panelConsultas);
				}
			});
		menuBar.add(altas);
		menuBar.add(bajas);
		menuBar.add(cambios);
		menuBar.add(consultas);
		setJMenuBar(menuBar);
		
		recordAltas.add(panelAltasTitulo);
		recordAltas.add(panelAltas);
		recordBajas.add(panelBajasTitulo);
		recordBajas.add(panelBajas);
		recordCambios.add(panelCambiosTitulo);
		recordCambios.add(panelCambios);
		recordConsultas.add(panelConsultasTitulo);
		recordConsultas.add(panelConsultas);
		
		dp.add(lista);
		dp.add(recordAltas);
		dp.add(recordBajas);
		dp.add(recordCambios);
		dp.add(recordConsultas);
		dp.setBounds(0, 0, 567, 425);
		add(dp);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==interaccion) {
			AlumnoDAO aDAO = new AlumnoDAO();
			if (recordAltas.isVisible()) {
				if (numControl.getText().equals("")||nombre.getText().equals("")||primerAp.getText().equals("")||segundoAp.getText().equals("")||comboEdad.getSelectedIndex()==-1||comboSemestre.getSelectedIndex()==-1||comboCarrera.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "Error: faltan uno o más campos");
				}else {
					Alumno a = new Alumno(numControl.getText(), nombre.getText(), primerAp.getText(), segundoAp.getText(),
							(byte)(comboEdad.getSelectedIndex()+1), (byte)(comboSemestre.getSelectedIndex()+1), comboCarrera.getSelectedItem().toString());
					if (aDAO.insertarRegistro(a)) {
						JOptionPane.showMessageDialog(null, "Alumno agregado exitosamente");
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar al alumno");
					}
				}
			}else if(recordBajas.isVisible()) {
				if (numControl.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se esta especificando el numero de control");
				}else {
					if (aDAO.eliminarRegistro(numControl.getText())) {
						JOptionPane.showMessageDialog(null, "Alumno eliminado exitosamente");
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar al alumno");
					}
				}
			}else if (recordCambios.isVisible()) {
				
				if (numControl.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se esta especificando el numero de control");
				}else {
					Alumno a = new Alumno(numControl.getText(), nombre.getText(), primerAp.getText(), segundoAp.getText(),
							(byte)(comboEdad.getSelectedIndex()+1), (byte)(comboSemestre.getSelectedIndex()+1), 
							comboCarrera.getSelectedIndex()!=-1?comboCarrera.getSelectedItem().toString():"");
					boolean flags[]=new boolean[6];
					flags[0]=!nombre.getText().equals("");
					flags[1]=!primerAp.getText().equals("");
					flags[2]=!segundoAp.getText().equals("");
					flags[3]=comboEdad.getSelectedIndex()!=-1;
					flags[4]=comboSemestre.getSelectedIndex()!=-1;
					flags[5]=comboCarrera.getSelectedIndex()!=-1;
					
					if (aDAO.modificarRegistro(a, flags)) {
						JOptionPane.showMessageDialog(null, "Datos de Alumno modificados exitosamente");
					}else {
						JOptionPane.showMessageDialog(null, "No se pudieron modificar los datos del alumno");
					}
				}
				
			}else if(recordConsultas.isVisible()) {
				if (numControl.getText().equals("")&&nombre.getText().equals("")&&primerAp.getText().equals("")&&segundoAp.getText().equals("")&&comboEdad.getSelectedIndex()==-1&&comboSemestre.getSelectedIndex()==-1&&comboCarrera.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "Error: no se lleno ningun campo");
				}else {
					ArrayList<Alumno> listaAlumnos = aDAO.buscarAlumnos("PARAMETROS");
					if (listaAlumnos.size()!=0) {
						JOptionPane.showMessageDialog(null, "Se encontraron registros que coinciden");
					}else {
						JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
					}
				}
				
			}
			actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");
		}
		if (arg0.getSource()==busqueda) {
			
			String sql = generadorConsulta();
			AlumnoDAO aDAO = new AlumnoDAO();
			
			actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos",sql);
			ArrayList<Alumno> alumnos = aDAO.buscarAlumnos(sql);
			
		}
		if (arg0.getSource()==borrar) {
			metodoQueRestableceTODO(numControl,nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);
		}
		if (arg0.getSource()==cancelar) {
			recordAltas.setVisible(false);
			recordBajas.setVisible(false);
			recordCambios.setVisible(false);
			recordConsultas.setVisible(false);
		}
		
	}
	
	public void metodoMagico(Component c, int x, int y,int width, int height, JPanel p) {
		p.add(c);
		c.setBounds(x, y, width, height);
	}
	
	public void metodoQueRestableceTODO(Component...componentesGraficos) {
		
		for (Component c: componentesGraficos) {
			if (c instanceof JComboBox) {
				((JComboBox<?>)c).setSelectedIndex(-1);
			}else if (c instanceof JTextField) {
				((JTextField)c).setText("");
			}
		}
		
	}
	
	public void actualizarTabla(String driver, String url, String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel(driver,url,sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

		lista.remove(sp);
		tabla = new JTable(modeloDatos);
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTabla();
		    }
		});
		sp = new JScrollPane(tabla);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setBounds(20,0,525,100);
		lista.add(sp);
		lista.setVisible(true);
	}
	
	public void obtenerRegistroTabla() {
		numControl.setText((String) tabla.getValueAt(tabla.getSelectedRow(),0));
		nombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(),1));
		primerAp.setText((String) tabla.getValueAt(tabla.getSelectedRow(),2));
		segundoAp.setText((String) tabla.getValueAt(tabla.getSelectedRow(),3));
		comboEdad.setSelectedIndex((int)(tabla.getValueAt(tabla.getSelectedRow(),4))-1);
		comboSemestre.setSelectedIndex((int)(tabla.getValueAt(tabla.getSelectedRow(),5))-1);
		comboCarrera.setSelectedItem(tabla.getValueAt(tabla.getSelectedRow(),6));
	}
	
	public String generadorConsulta() {
		AlumnoDAO aDAO = new AlumnoDAO();
		String sql = "SELECT * FROM Alumnos ";
		boolean primero=true;
		
		if (!numControl.getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("NumControl='"+numControl.getText()+"'");
		}
		if (!nombre.getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Nombre='"+nombre.getText()+"'");
		}
		if (!primerAp.getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PrimerAp='"+primerAp.getText()+"'");
		}
		if (!segundoAp.getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("SegundoAp='"+segundoAp.getText()+"'");
		}
		if (comboEdad.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Edad="+comboEdad.getSelectedItem());
		}
		if (comboSemestre.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Semestre="+comboSemestre.getSelectedItem());
		}
		if (comboCarrera.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Carrera='"+comboCarrera.getSelectedItem()+"'");
		}
		
		return sql;
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
