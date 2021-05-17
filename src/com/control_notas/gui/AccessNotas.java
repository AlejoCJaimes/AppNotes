/**
 * @DAO Lawyer
 *
 * Now, let’s try to perform some operations on our data store using
 * the created persistence layer:
 */
package com.control_notas.gui;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Jaimes
 */
public class AccessNotas {

    private static ArrayList<String> materias;
    private static ArrayList<Integer> nEstudiantes;
    private static ArrayList<Integer> ubiSemestral;

    public static void main(String[] args) {
        info();
        jFramePrincipalDocente pDoc = new jFramePrincipalDocente(materias, nEstudiantes, ubiSemestral);
        pDoc.setVisible(true);
    }

    private static void info() {
        materias = new ArrayList<>();
        nEstudiantes = new ArrayList<>();
        ubiSemestral = new ArrayList<>();

        materias.add("Cálculo Diferencial");
        materias.add("Cálculo Integral");
        materias.add("Cálculo Multivaribale");
        materias.add("Programación Orientada a Objetos");
        materias.add("Programación WEB en JavaScript y Angular");
        materias.add("Estadística y Probabilidad");
        materias.add("Teoria de la Computación");
        materias.add("Bases de Datos 2");

        nEstudiantes.add(20);
        nEstudiantes.add(10);
        nEstudiantes.add(15);
        nEstudiantes.add(20);
        nEstudiantes.add(9);
        nEstudiantes.add(15);
        nEstudiantes.add(8);
        nEstudiantes.add(4);

        ubiSemestral.add(1);
        ubiSemestral.add(2);
        ubiSemestral.add(3);
        ubiSemestral.add(2);
        ubiSemestral.add(5);
        ubiSemestral.add(2);
        ubiSemestral.add(5);
        ubiSemestral.add(6);
    }

}
