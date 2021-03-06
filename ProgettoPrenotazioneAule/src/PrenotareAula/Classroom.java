/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import java.util.Date;

/**
 * This class represents a classroom with a name and its requirements.
 * Classroom provides methods for verifying its availability, in the case of a request of reservation.
 * 
 * @author Andrea
 */
public class Classroom implements Comparable<Classroom> {

    private String name;
    private Requirements req;
    private ReservationRegister resReg;

    public Classroom(String name, Requirements req) {
        this.name = name;
        this.req = req;
        resReg = new ReservationRegister(name);
    }

    /**
     * This method verifies if a classroom respects the requirements and if it
     * is free.
     *
     * @param req the requirements of the classroom
     * @param d date of the request reservation
     * @param startHour start time of the request reservation
     * @param endHour end time of the request reservation
     * @return integer value: 1 if the classroom respects the requirements and
     * is free, 2 if the classroom is not free, for other return values see
     * checkRequirements() documentation
     */
    public int verifyReservation(Requirements req, Date d, int startHour, int endHour) {
        if (checkSpecial(req)) {
            if ((checkRequirements(req) == 0) && resReg.isReserved(d, startHour, endHour) == false) {
                //System.out.println("Trovata aula libera che soddisfa i requisiti richiesti: " + this.name);
                return 1;
            }
            if ((checkRequirements(req) != 0) && resReg.isReserved(d, startHour, endHour) == false) {
                //System.out.println("Aula non disponibile");
                return checkRequirements(req);
            }
        }
        return 2;
    }

    /**
     * This method checks if a classroom has the request requirements. The most
     * important requirement is the capacity of the classroom and then in order
     * blackboard, projector, whiteboard.
     *
     * @param r the requirements of the classroom
     * @return integer value: 0 -> all requirements are present in the classroom
     * -1 -> capacity not respected, -2 -> no blackboard, -3 -> no projector, -4
     * -> no whiteboard
     */
    private int checkRequirements(Requirements r) {
        int i = 0;
        if ((this.req.isWhiteboard() == false) && r.isWhiteboard()) {
            i = -4; //no whiteboard
        }
        if ((this.req.isProjector() == false) && r.isProjector()) {
            i = -3; //no projector
        }
        if ((this.req.isBlackboard() == false) && r.isBlackboard()) {
            i = -2; //no blackboard
        }
        if (this.req.getCapacity() < r.getCapacity()) {
            i = -1; //no capacity
        }
        return i;
    }
    
    /**
     * This method checks if a classroom has the special request requirements.
     * 
     * @param r the requirements of the classroom
     * @return boolean value
     */
    private boolean checkSpecial(Requirements r) {
        if ((this.req.getSpecialRequirements() == null) && (r.getSpecialRequirements() == null)) {
            return true;
        } else if ((this.req.getSpecialRequirements() != null) && (r.getSpecialRequirements() != null) && (this.req.getSpecialRequirements().equals(r.getSpecialRequirements()))) {
            return true;
        }
        return false;
    }
    
    /**
     * compareTo compares capacity of a couple of classroom.
     *
     * @param cl classroom, its capacity is to be compared
     * @return compareTo returns a negative integer (-1), zero, or a positive
     * integer (+1) as this capacity is less than, equal to, or greater than the
     * capacity of the classroom given as the argument of the method.
     */
    @Override
    public int compareTo(Classroom cl) {
        if (this.req.getCapacity() < cl.getRequirements().getCapacity()) {
            return -1;
        } else if (this.req.getCapacity() == cl.getRequirements().getCapacity()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * If there exists at least one reservation, the method prints the
     * reservation register of the classroom.
     *
     * @return a string with the details of each reservation of the classroom
     */
    public String printClassroom() {
        String s = "\n*****************\nAula: " + this.name + "\n";
        if (this.resReg.printRegister().equals("")) {
            s += "Nessuna prenotazione effettuata";
        } else {
            s += this.resReg.printRegister();
        }
        return s;
    }

    public String getName() {
        return name;
    }

    public Requirements getRequirements() {
        return req;
    }

    public ReservationRegister getResReg() {
        return resReg;
    }
}
