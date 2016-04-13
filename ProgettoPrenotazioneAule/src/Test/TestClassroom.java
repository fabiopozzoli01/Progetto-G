/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import PrenotareAula.Classroom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class TestClassroom {

    public static void main(String[] args) {
        Classroom ef1 = new Classroom("EF1", 280);
        Classroom ef2 = new Classroom("EF2", 250);
        Classroom c5 = new Classroom("C5", 20);
        Classroom a1 = new Classroom("A1", 180);

        Calendar cal = new GregorianCalendar(2016, 3, 5);

        ef1.verifyReservation(0, cal, 9, 11);
        ef1.verifyReservation(280, cal, 9, 11);

        List<Classroom> classi = new ArrayList();
        classi.add(ef1);
        classi.add(ef2);
        classi.add(c5);
        classi.add(a1);
        Collections.sort(classi);
        for (Classroom cl : classi) {
            System.out.println(cl.getName() + " : " + cl.getCapacity());
        }
    }
}
