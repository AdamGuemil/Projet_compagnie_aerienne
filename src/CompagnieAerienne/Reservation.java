package CompagnieAerienne;

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
}
