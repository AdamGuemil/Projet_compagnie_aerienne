package CompagnieAerienne;

public class NumeroReservation {
    static int nbId = 1;
    public int id;
    public Passager p;
    public Reservation r;

    public NumeroReservation(Passager p, Reservation r) {
        id     = nbId++;
        this.p = p;
        this.r = r;
    }
}
