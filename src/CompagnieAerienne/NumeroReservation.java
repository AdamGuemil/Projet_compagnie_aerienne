package CompagnieAerienne;

public class NumeroReservation {
    static int nbId;
    int id;
    Passager p;
    Reservation r;
    public NumeroReservation(Passager  p ,Reservation r){
        id = nbId++;
        this.p = p;
        this.r = r;
    }

}
