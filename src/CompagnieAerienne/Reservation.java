//TODO faudrait rajouter import java.time.LocalDate pour pouvoir un systeme de resa qui se calle avec l'heure et la date

package CompagnieAerienne;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservation {
    public NumeroReservation numR;
    public NumeroVol numV;
    public int DateReservation;
    public int numeroSiege;

    public Reservation(NumeroVol numV,NumeroReservation numR){
        numR.r = this;
        this.numR = numR;
        this.numV = numV;
    }

    public static boolean CheckReservation(Reservation[] listeReservations, int id){
        for (int i=0;i<listeReservations.length;i++){
            if (listeReservations[i].numR.id == id){
                return true;
            }
        }
        return false;
    }

    public static NumeroReservation getNumeroReservation(Reservation[] listeReservations, int id){
        for (int i=0;i<listeReservations.length;i++){
            if (listeReservations[i].numR.id == id){
                return listeReservations[i].numR;
            }
        }
        return null;
    }

    public static int MakeReservation(){
        Scanner mySeat = new Scanner(System.in);
        System.out.println("Veuillez reserver une place, s'il-vous-plait");// Dans notre cas, on considere qu'on reserve une place aleatorie

        int seatTaken = mySeat.nextInt(); //Lit la valeur prise par l'utilisateur

        System.out.println("Merci, votre reservation a bien ete prise en compte.");
        return 0;
    }
}
