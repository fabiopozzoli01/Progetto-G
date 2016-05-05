/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TUI;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Federico
 */
public class TextUserInterface {
    public static void main(String[] args) throws IOException {
        boolean exit=false;
        Campus cp=new Campus("XClassLiveCampus");
        System.out.println("benvenuto in XClassLive, software di prenotazione aule, selezionare un opzione:");
        Scanner tastiera=new Scanner(System.in);
        while (exit==false) {
        System.out.println("1) prenotare un'aula");
        System.out.println("2) annullare una prenotazione");
        System.out.println("3) stampare tutte le prenotazioni");
        System.out.println("4) esci");
        
        switch(tastiera.nextInt()) {
            case(1): 
                prenotazione(cp);
                break;
            case(2): 
                rimuoviPrenotazione(cp);
                break;
            case(3):
                cp.printAllClassroomReservation();
                break;
            case(4):
                exit=true;
                break;
            default: 
                System.out.println("\n selezionare una delle opzioni presenti\n");
                break;
        }
        }
    }
    
    public static void prenotazione(Campus cp) throws IOException {
        Scanner tastieraPrenotazione=new Scanner(System.in);
        System.out.println("inserire la capacità dell'aula che si vuole prenotare");
        int capacity=tastieraPrenotazione.nextInt();
        Requirements req=new Requirements(capacity, true, true, true, null);
        System.out.println("inserire la data in cui si vuole effettuare la prenotazione aaaa/mm/dd");
        String data=tastieraPrenotazione.next();
        StringTokenizer st= new StringTokenizer(data);
        Calendar ca=new GregorianCalendar();
        ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")));
        System.out.println("inserire ora inizio prenotazione");
        int startHour=tastieraPrenotazione.nextInt();
        System.out.println("inserire ora fine prenotazione");
        int endHour=tastieraPrenotazione.nextInt();
        cp.askForReservation(req, ca, startHour, endHour);
        
        
    }
    
    public static void rimuoviPrenotazione(Campus cp) {
        Scanner tastieraPrenotazione=new Scanner(System.in);
        System.out.println("specifica l'id della prenotazione che si vuole rimuovere");
        int id=tastieraPrenotazione.nextInt();
        if(cp.deleteReservation(id)==true) {
            System.out.println("prenotazione cancellata");
            
        }
        else {
            System.out.println("non è stata trovata una prenotazione con l'id specificato");
        }
    }
    
}
