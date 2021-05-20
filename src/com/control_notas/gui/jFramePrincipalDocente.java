package com.control_notas.gui;

import com.control_notas.dao.DocenteDao;
import com.control_notas.daoimpl.DocenteDaoImpl;
import com.control_notas.model.Clases;
import com.control_notas.model.Docente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class jFramePrincipalDocente extends JFrame {

   

    protected String nombreDocente;
    protected int id_usuario;
    
    public jFramePrincipalDocente(int idUsuario) {
        inicializarEntorno();
        this.id_usuario = idUsuario;
        inicializarRecursos();
        
    }

    private void inicializarRecursos(){
        //Atributes
        //Dao Docente
        DocenteDao daoDoc = new DocenteDaoImpl();

        //Instancia Docente
        Docente _doc = new Docente();
        
        //Lista Clases
        List<Clases> listaCursosDocente = null;
        //Cursos docente
        int cursos = 0;

        try {
            _doc = obtenerInformacionDocente(daoDoc, _doc);
            cursos = obtenerCursosDocente(daoDoc);
            listaCursosDocente = daoDoc.listarDashboarDocente(id_usuario);
            nombreDocente = _doc.getNombre();
        } catch (Exception ex) {
            Logger.getLogger(jFramePrincipalDocente.class.getName()).log(Level.SEVERE, null, ex);
        }

        jPanelInformationDocente panInformationDocente = new jPanelInformationDocente(_doc,cursos);

        this.add(panInformationDocente, BorderLayout.WEST);
        //PASAR LISTA
        jPanelContentInformationCourse d = new jPanelContentInformationCourse(listaCursosDocente);
        this.add(d, BorderLayout.CENTER);
        this.add(getEncabezado(), BorderLayout.NORTH);

        JLabel pie = new JLabel("By: AlejandroJaimes & VolkmarCarrillo");
        pie.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pie, BorderLayout.SOUTH);
    }
    
    private String obtenerRutaIcono () {
        String ruta = ".\\src\\com\\control_notas\\gui\\images\\icon_application.png";
        return ruta;
    }

    private Docente obtenerInformacionDocente(DocenteDao daoDoc, Docente _doc) throws Exception {
        
        try {
            _doc = daoDoc.obtenerDocente(33);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return _doc;
    }
    
    private int obtenerCursosDocente (DocenteDao daoDoc) throws Exception {
        int cursos = 0;
         try {
            cursos = daoDoc.cursosDocente(33);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return cursos;
        
    }

    private JPanel getEncabezado() {
        JPanel encabezado = new JPanel();
        Box box = new Box(BoxLayout.Y_AXIS);
        
        
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.PAGE_AXIS));
        encabezado.setBackground(new Color(18, 17, 95));
        encabezado.setBorder(BorderFactory.createTitledBorder(encabezado.getBorder(), "Home", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
                 encabezado.getFont(), Color.WHITE));
        JLabel welcome = new JLabel("--- BIENVENIDO "+ nombreDocente.toUpperCase()+" ---");
        
        welcome.setFont(new Font("", Font.BOLD, 20));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(welcome);

        box.add(Box.createVerticalStrut(15));

        JLabel date = new JLabel("INGRESO: " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        date.setForeground(Color.WHITE);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(date);

        encabezado.add(box);
        encabezado.setVisible(true);
        return (encabezado);
    }

    private void inicializarEntorno() {
        String url = obtenerRutaIcono();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Control de Notas DOCENTE (Demo Personalizado)");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }
   
}
