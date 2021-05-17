
package com.control_notas.gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class jFrameTableStudents extends JFrame{
    
    public jFrameTableStudents(ArrayList<Integer> codigos, ArrayList<String> nombres, ArrayList<String> apellidos,
                                ArrayList<String> correos, ArrayList<Double> corte1, ArrayList<Double> corte2, ArrayList<Double> corte3, int index){
        
        this.add(new JScrollPane(this.getTableStudents(codigos, nombres, apellidos, correos, corte1, corte2, corte3)));
        this.inicializarEntorno(index);
    }
    
    private JTable getTableStudents(ArrayList<Integer> codigos, ArrayList<String> nombres, ArrayList<String> apellidos,
                                    ArrayList<String> correos, ArrayList<Double> corte1, ArrayList<Double> corte2, ArrayList<Double> corte3){
        String[] columns = this.columns();
        Object[][] data = this.data(codigos, nombres, apellidos, correos, corte1, corte2, corte3);
        
        DefaultTableModel students = new DefaultTableModel(data, columns);
        
        JTable tableStudents = new JTable(students);
        tableStudents.setRowHeight(30);
        
        //tableStudents.getColumn("Corte 1").setCellRenderer(new AuxiliarJTableNotas(tableStudents, tableStudents.getSelectedRow(), tableStudents.getSelectedColumn()));
        //tableStudents.getColumn("Corte 1").setCellEditor(new AuxiliarJTableNotas(tableStudents, tableStudents.getSelectedRow(), tableStudents.getSelectedColumn()));
//        
//        tableStudents.getColumn("Corte 2").setCellRenderer(new AuxiliarJTableNotas(tableStudents));
//        tableStudents.getColumn("Corte 2").setCellEditor(new AuxiliarJTableNotas(tableStudents));
//        
//        tableStudents.getColumn("Corte 3").setCellRenderer(new AuxiliarJTableNotas(tableStudents));
//        tableStudents.getColumn("Corte 3").setCellEditor(new AuxiliarJTableNotas(tableStudents));
        
        return(tableStudents);
    }
    
    private String[] columns(){
        String[] columns = { "Código", "Nombres", "Apellidos", "Correo Electrónico", 
                                        "Corte 1", "Corte 2", "Corte 3", "Definitiva"};
        return columns;
    }
    
    private Object[][] data(ArrayList<Integer> codigos, ArrayList<String> nombres, ArrayList<String> apellidos,
                                ArrayList<String> correos, ArrayList<Double> corte1, ArrayList<Double> corte2, ArrayList<Double> corte3){
        int size = codigos.size();
        Object[][] d = new Object[size][8];
        
        for(int i=0; i< size; i++){
            d[i][0] = codigos.get(i);
            d[i][1] = nombres.get(i);
            d[i][2] = apellidos.get(i);
            d[i][3] = correos.get(i);
            d[i][4] = corte1.get(i);
            d[i][5] = corte2.get(i);
            d[i][6] = corte3.get(i);
            d[i][7] = 0.0;
        }
        
        return d;
    }
    
    private void inicializarEntorno(int index) {        
        this.setTitle("Estudiantes Curso "+index);
        this.setSize(600, 200);
        
        this.setVisible(true);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
//    public static void main(String []args){
//        ArrayList<Integer> codigos;
//        codigos = new ArrayList<>();
//        
//        ArrayList<String> nombres;
//        nombres = new ArrayList<>();
//        
//        ArrayList<String> apellidos;
//        apellidos = new ArrayList<>();
//        
//        ArrayList<String> correos;
//        correos = new ArrayList<>();
//        
//        ArrayList<Double> corte1;
//        corte1 = new ArrayList<>();
//        
//        ArrayList<Double> corte2;
//        corte2 = new ArrayList<>();
//        
//        ArrayList<Double> corte3;
//        corte3 = new ArrayList<>();
//        
//        codigos.add(001);
//        codigos.add(002);
//        codigos.add(003);
//        
//        nombres.add("Volkmar");
//        nombres.add("Alejandro");
//        nombres.add("Valeria");
//        
//        apellidos.add("Carrillo");
//        apellidos.add("Jaimes");
//        apellidos.add("Contreras");
//        
//        correos.add("volkmar.carrillo@gmail.com");
//        correos.add("alejandro.jaimes@gmail.com");
//        correos.add("valeria.contreras@gmail.com");
//        
//        corte1.add(4.2);
//        corte1.add(4.2);
//        corte1.add(4.2);
//        
//        corte2.add(4.2);
//        corte2.add(4.2);
//        corte2.add(4.2);
//        
//        corte3.add(4.2);
//        corte3.add(4.2);
//        corte3.add(4.2);
//        
//        
//        
//        new jFrameTableStudents(codigos, nombres, apellidos,correos, corte1, corte2, corte3, 1);
//    }
}
