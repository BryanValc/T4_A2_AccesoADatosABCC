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

class Interfaz extends JFrame implements ActionListener, ItemListener{
	
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
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(584,490);
		setLocationRelativeTo(null);
		setTitle("Formulario");
		setVisible(true);
		
		reasignacion();
		
		JDesktopPane dp = new JDesktopPane();
		
		recordAltas = new JInternalFrame();//==============================================Frame Altas====================================================
		JPanel panelesAltas[]= defPanel(recordAltas, "Altas Alumnos", "ALTAS ALUMNOS", Color.GREEN);
		recordBajas = new JInternalFrame();//==============================================Frame Bajas====================================================
		JPanel panelesBajas[]= defPanel(recordBajas, "Bajas Alumnos", "BAJAS ALUMNOS", Color.RED);
		recordCambios = new JInternalFrame();//=========================================Frame Cambios=====================================================
		JPanel panelesCambios[]= defPanel(recordCambios, "Modificaciones Alumnos", "MODIFICACIONES ALUMNOS", Color.ORANGE);
		recordConsultas = new JInternalFrame();//========================================Frame Consultas==================================================
		JPanel panelesConsultas[]= defPanel(recordConsultas, "Consultas Alumnos", "CONSULTAS ALUMNOS", Color.BLUE);
			
		lista = new JInternalFrame();//========================================Frame Lista================================================================
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
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");//tabla
					recordAltas.setVisible(true);//JFrames
					recordBajas.setVisible(false);
					recordCambios.setVisible(false);
					recordConsultas.setVisible(false);
					setFormularioEnabled(true);//Formulario y elementos
					defPosicionamiento(panelesAltas[1], false, true, "AGREGAR");
				}
			});	
		bajas = new JMenu("Bajas");
			menuItemBajas= new JMenuItem("eliminar");
			bajas.add(menuItemBajas);
			menuItemBajas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");//tabla
					recordAltas.setVisible(false);//JFrames
					recordBajas.setVisible(true);
					recordCambios.setVisible(false);
					recordConsultas.setVisible(false);
					metodoQueRestableceTODO(nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);//Formulario y elementos
					setFormularioEnabled(false);
					numControl.setEditable(true);
					defPosicionamiento(panelesBajas[1], true, true, "ELIMINAR");
					
				}
			});	
		cambios = new JMenu("Cambios");
			menuItemCambios= new JMenuItem("modificar");
			cambios.add(menuItemCambios);
			menuItemCambios.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");//tabla
					recordAltas.setVisible(false);//JFrames
					recordBajas.setVisible(false);
					recordCambios.setVisible(true);
					recordConsultas.setVisible(false);
					setFormularioEnabled(true);//Formulario y elementos
					defPosicionamiento(panelesCambios[1], true, true, "GUARDAR CAMBIOS");
					
				}
			});
		consultas = new JMenu("Consultas");
			menuItemConsultas= new JMenuItem("buscar");
			consultas.add(menuItemConsultas);
			menuItemConsultas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actualizarTabla("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/Escuela_Topicos","SELECT * FROM Alumnos");//tabla
					recordAltas.setVisible(false);//JFrames
					recordBajas.setVisible(false);
					recordCambios.setVisible(false);
					recordConsultas.setVisible(true);
					metodoQueRestableceTODO(numControl,nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);
					setFormularioEnabled(false);
					cbTodos.setSelected(false);
					setCheckboxesSelected(false);
					defPosicionamiento(panelesConsultas[1], true, false, "");;//Formulario y elementos
					metodoMagico(cbTodos, 70, 17, 20, 20, panelesConsultas[1]);//Checkboxes
					metodoMagico(cbNumeroDeControl, 70, 37, 20, 20, panelesConsultas[1]);
					metodoMagico(cbNombres, 70, 60, 20, 20, panelesConsultas[1]);
					metodoMagico(cbApellidoPaterno, 70, 83, 20, 20, panelesConsultas[1]);
					metodoMagico(cbApellidoMaterno, 70, 106, 20, 20, panelesConsultas[1]);
					metodoMagico(cbEdad, 70, 129, 20, 20, panelesConsultas[1]);
					metodoMagico(cbSemestre, 70, 159, 20, 20, panelesConsultas[1]);
					metodoMagico(cbCarrera, 70, 177, 20, 20, panelesConsultas[1]);
				}
			});
		menuBar.add(altas);
		menuBar.add(bajas);
		menuBar.add(cambios);
		menuBar.add(consultas);
		setJMenuBar(menuBar);
		
		recordAltas.add(panelesAltas[0]);
		recordAltas.add(panelesAltas[1]);
		recordBajas.add(panelesBajas[0]);
		recordBajas.add(panelesBajas[1]);
		recordCambios.add(panelesCambios[0]);
		recordCambios.add(panelesCambios[1]);
		recordConsultas.add(panelesConsultas[0]);
		recordConsultas.add(panelesConsultas[1]);
		
		dp.add(lista);
		dp.add(recordAltas);
		dp.add(recordBajas);
		dp.add(recordCambios);
		dp.add(recordConsultas);
		dp.setBounds(0, 0, 567, 425);
		add(dp);
		
	}
	
	public void reasignacion() {
		numControl = new JTextField();//===========================Formulario
		nombre = new JTextField();
		primerAp = new JTextField();
		segundoAp = new JTextField();
		comboEdad = new JComboBox<String>();
		comboSemestre = new JComboBox<String>();
		comboCarrera = new JComboBox<String>();
		for (int i = 1; i <= 122; i++) {	comboEdad.addItem(""+i);}//========ComboBoxes
		for (int i = 1; i <= 10; i++) {		comboSemestre.addItem(""+i);}
		comboCarrera.addItem("ISC");
		comboCarrera.addItem("IM");
		comboCarrera.addItem("ADMON");
		comboCarrera.addItem("IIA");
		comboCarrera.addItem("LEC");
		metodoQueRestableceTODO(comboEdad,comboSemestre,comboCarrera);
		lblNumeroDeControl=new JLabel("NUMERO DE CONTROL:");//================Labels
		lblNombres=new JLabel("NOMBRE:");
		lblApellidoPaterno=new JLabel("APELLIDO PATERNO:");
		lblApellidoMaterno=new JLabel("APELLIDO MATERNO:");
		lblEdad=new JLabel("EDAD:");
		lblSemestre=new JLabel("SEMESTRE:");
		lblCarrera=new JLabel("CARRERA:");
		borrar = new JButton("BORRAR");//===================Interacciones
		borrar.addActionListener(this);
		interaccion = new JButton("");
		interaccion.addActionListener(this);
		cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(this);
		busqueda = new JButton(iconoBusqueda);
		busqueda.addActionListener(this);
		cbTodos=new JCheckBox();//========================checkBoxes
		cbNumeroDeControl=new JCheckBox();
		cbNombres=new JCheckBox();
		cbApellidoPaterno=new JCheckBox();
		cbApellidoMaterno=new JCheckBox();
		cbEdad=new JCheckBox();
		cbSemestre=new JCheckBox();
		cbCarrera=new JCheckBox();
		cbTodos.addItemListener(this);//================checkBoxes itemListener
		cbNumeroDeControl.addItemListener(this);
		cbNombres.addItemListener(this);
		cbApellidoPaterno.addItemListener(this);
		cbApellidoMaterno.addItemListener(this);
		cbEdad.addItemListener(this);
		cbSemestre.addItemListener(this);
		cbCarrera.addItemListener(this);
	}
	
	public void setCheckboxesSelected(boolean b){
		cbNombres.setSelected(b);
		cbApellidoPaterno.setSelected(b);
		cbApellidoMaterno.setSelected(b);
		cbEdad.setSelected(b);
		cbSemestre.setSelected(b);
		cbCarrera.setSelected(b);
	}
	public void setCheckboxesEnabled(boolean b){
		cbNumeroDeControl.setEnabled(b);
		cbNombres.setEnabled(b);
		cbApellidoPaterno.setEnabled(b);
		cbApellidoMaterno.setEnabled(b);
		cbEdad.setEnabled(b);
		cbSemestre.setEnabled(b);
		cbCarrera.setEnabled(b);
	}
	public void setFormularioEnabled(boolean b) {
		numControl.setEditable(b);
		nombre.setEditable(b);
		primerAp.setEditable(b);
		segundoAp.setEditable(b);
		comboEdad.setEnabled(b);
		comboSemestre.setEnabled(b);
		comboCarrera.setEnabled(b);
	}
	
	public JPanel[] defPanel(JInternalFrame frame,String encabezado,String titulo,Color color) {
		frame.getContentPane().setLayout(null);//Frame
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setSize(567,290);
		frame.setTitle(encabezado);
		JPanel panelTitulo = new JPanel();//Panel titulo
		panelTitulo.setLayout(null);
		panelTitulo.setBackground(color);
		panelTitulo.setBounds(0, 0, 567, 50);
		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(Color.WHITE);
		metodoMagico(lblTitulo, 30, 20, 290, 20, panelTitulo);
		JPanel panel = new JPanel();//Panel
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 50, 567, 425);
		JPanel retorno[]=new JPanel[2];
		retorno[0]=panelTitulo;
		retorno[1]=panel;
		return retorno;
	};
	public void defPosicionamiento(JPanel panel, boolean busq, boolean interact, String boton) {
		metodoMagico(lblNumeroDeControl, 90, 37, 130, 20, panel);//Labels
		metodoMagico(lblNombres, 90, 60, 130, 20, panel);
		metodoMagico(lblApellidoPaterno, 90, 83, 130, 20, panel);
		metodoMagico(lblApellidoMaterno, 90, 106, 130, 20, panel);
		metodoMagico(lblEdad, 90, 129, 130, 20, panel);
		metodoMagico(lblSemestre, 90, 159, 130, 20, panel);
		metodoMagico(lblCarrera, 90, 177, 130, 20, panel);
		metodoMagico(numControl, 226, 40, 134, 17, panel);//Posicionamiento
		metodoMagico(nombre, 172, 63, 187, 17, panel);
		metodoMagico(primerAp, 216, 86, 144, 17, panel);
		metodoMagico(segundoAp, 216, 109, 144, 17, panel);
		metodoMagico(comboEdad, 216, 137, 40, 16, panel);
		metodoMagico(comboSemestre, 216, 165, 144, 16, panel);
		metodoMagico(comboCarrera, 216, 181, 144, 16, panel);
		metodoMagico(borrar, 380, 46, 90, 18, panel);//Interacciones
		metodoMagico(cancelar, 375, 153, 100, 18, panel);
		if (busq) {metodoMagico(busqueda, 380, 11, 84, 30, panel);}
		if (interact) {
			interaccion.setText(boton);
			if (boton.contains("GUARDAR CAMBIOS")) {
				metodoMagico(interaccion, 380, 105, 160, 18, panel);
			}else {
				metodoMagico(interaccion, 380, 105, 90, 18, panel);
			}
			
		}
	}
	
	public void metodoMagico(Component c, int x, int y,int width, int height, JPanel p) {
		c.setBounds(x, y, width, height);
		p.add(c);
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

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object src = arg0.getSource();
		if (src==cbTodos) {
			if(cbTodos.isSelected()) {
				setCheckboxesSelected(false);
				setCheckboxesEnabled(false);
				setFormularioEnabled(true);
            }else {
            	metodoQueRestableceTODO(numControl,nombre,primerAp,segundoAp,comboEdad,comboSemestre,comboCarrera);
            	setFormularioEnabled(false);
            	setCheckboxesEnabled(true);
            }
		}else if(src==cbNumeroDeControl) {
			if(cbNumeroDeControl.isSelected()) {numControl.setEditable(true);
            }else {
            	numControl.setText("");
            	numControl.setEditable(false);
            }
		}else if (src==cbNombres) {
			if(cbNombres.isSelected()) {nombre.setEditable(true);
            }else {
            	nombre.setText("");
            	nombre.setEditable(false);
            }
		}else if (src==cbApellidoPaterno) {
			if(cbApellidoPaterno.isSelected()) {primerAp.setEditable(true);
            }else {
            	primerAp.setText("");
            	primerAp.setEditable(false);
            }
		}else if (src==cbApellidoMaterno) {
			if(cbApellidoMaterno.isSelected()) {segundoAp.setEditable(true);
            }else {
            	segundoAp.setText("");
            	segundoAp.setEditable(false);
            }
		}else if (src==cbEdad) {
			if(cbEdad.isSelected()) {comboEdad.setEnabled(true);
            }else {
            	comboEdad.setSelectedIndex(-1);
            	comboEdad.setEnabled(false);
            }
		}else if (src==cbSemestre) {
			if(cbSemestre.isSelected()) {comboSemestre.setEnabled(true);
            }else {
            	comboSemestre.setSelectedIndex(-1);
            	comboSemestre.setEnabled(false);
            }
		}else if (src==cbCarrera) {
			if(cbCarrera.isSelected()) {comboCarrera.setEnabled(true);
            }else {
            	comboCarrera.setSelectedIndex(-1);
            	comboCarrera.setEnabled(false);
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
