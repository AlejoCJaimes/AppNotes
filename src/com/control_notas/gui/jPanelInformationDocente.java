package com.control_notas.gui;

import com.control_notas.model.Docente;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class jPanelInformationDocente extends JPanel{

    private final JLabel logout = new JLabel();

    public jPanelInformationDocente(Docente _doc, int cursos) {
        this.inicializarRecursos(_doc, cursos);
        this.inicializarEntorno();
    }

    private void inicializarRecursos(Docente _doc, int cursosCargo) {
        //Alineación de elementos en el eje Y
        Box box = new Box(BoxLayout.Y_AXIS);
        //Imagen de Usuario
        JLabel imageUser = new JLabel();
        imageUser.setIcon(new ImageIcon(obtenerRutaIcono("icon_user.png")));
        imageUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        //___________________________________________________________
        logout.setIcon(new ImageIcon(obtenerRutaIcono("icon_logout.png")));
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);

        logout.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent me) {
               System.exit(0);
             
               jFrameLogin frm_login = new jFrameLogin();
               frm_login.setVisible(true);
            };
        });
            //___________________________________________________________
        box.add(imageUser);
        //___________________________________________________________
        JLabel correo = new JLabel("\n"+_doc.getCorreo());
        correo.setAlignmentX(Component.CENTER_ALIGNMENT);
        correo.setForeground(Color.WHITE);
        box.add(correo);
        //___________________________________________________________
        
        JLabel titulo = new JLabel(_doc.getTituloDocente());
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(Color.WHITE);
        box.add(titulo);
        //___________________________________________________________
        
        //Espaciado entre elementos
        box.add(Box.createVerticalStrut(100));
        //___________________________________________________________
        //Agregarmos la informacion personal
        
        box.add(getPanelDatos(_doc, cursosCargo));
        //Button de Salir       
        box.add(Box.createVerticalStrut(100));
        box.add(logout);
        box.add(Box.createVerticalStrut(50));
        this.add(box);
    }
    
    private String obtenerRutaIcono (String urlImage) {
        String ruta = ".\\src\\com\\control_notas\\gui\\images\\"+ urlImage;
        return ruta;
    }

//    private JButton getCerrarSesion(){
//        JButton cerrarSesion = new JButton("Cerrar Sesión");        
//        cerrarSesion.setBackground(new Color(155, 9, 9));
//        cerrarSesion.setForeground(Color.WHITE);
//        cerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
//        cerrarSesion.setToolTipText("Cerrar Sesión");
//        cerrarSesion.setSize(40, 20);
//        
//        return(cerrarSesion);
//    }
    private JPanel getPanelDatos(Docente _doc, int cursosCargo) {
        JPanel datos = new JPanel();
        datos.setLayout(new BoxLayout(datos, BoxLayout.PAGE_AXIS));

        jPanelRefInformation p1 = new jPanelRefInformation(new JLabel("Identificación: "), new JTextField(_doc.getNum_identificacion()));
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        datos.add(p1);

        jPanelRefInformation p2 = new jPanelRefInformation(new JLabel("Nombres: "), new JTextField(_doc.getNombre()));
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        datos.add(p2);

        jPanelRefInformation p3 = new jPanelRefInformation(new JLabel("Apellidos: "), new JTextField(_doc.getApellido()));
        p3.setLayout(new FlowLayout(FlowLayout.CENTER));
        datos.add(p3);

        jPanelRefInformation p4 = new jPanelRefInformation(new JLabel("Cursos a cargo: "), new JTextField(String.valueOf(cursosCargo)));
        p4.setLayout(new FlowLayout(FlowLayout.CENTER));
        datos.add(p4);

        datos.setBackground(new Color(18, 17, 95));
        datos.setBorder(BorderFactory.createTitledBorder(this.getBorder(), "Datos Personales", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
                this.getFont(), Color.WHITE));
        return (datos);
    }

    private void inicializarEntorno() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(new Color(18, 17, 95));
        this.setSize(500, 800);
        this.setBorder(BorderFactory.createTitledBorder(this.getBorder(), "Información Docente", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
                this.getFont(), Color.WHITE));
        this.setVisible(true);
    }
    
}
