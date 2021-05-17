package com.control_notas.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class jFramePrincipalDocente extends JFrame {

    public jFramePrincipalDocente(ArrayList<String> materias, ArrayList<Integer> nEstudiantes, ArrayList<Integer> ubiSemestral) {
        inicializarRecursos(materias, nEstudiantes, ubiSemestral);
        inicializarEntorno();
    }

    private void inicializarRecursos(ArrayList<String> materias, ArrayList<Integer> nEstudiantes, ArrayList<Integer> ubiSemestral) {

        jPanelInformationDocente p = new jPanelInformationDocente("Volkmar Adyya", "Carrillo Parada", "1005037107", "volkmar_carrillo@outlook.com", 25);
        this.add(p, BorderLayout.WEST);

        jPanelContentInformationCourse d = new jPanelContentInformationCourse(materias, nEstudiantes, ubiSemestral);
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

    

    private JPanel getEncabezado() {
        JPanel encabezado = new JPanel();
        Box box = new Box(BoxLayout.Y_AXIS);

        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.PAGE_AXIS));
        encabezado.setBackground(new Color(18, 17, 95));
        encabezado.setBorder(BorderFactory.createTitledBorder(encabezado.getBorder(), "Home", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
                 encabezado.getFont(), Color.WHITE));
        JLabel welcome = new JLabel("--- BIENVENIDO DOCENTE ---");
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
