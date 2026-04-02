package CompagnieAerienne;

import java.util.ArrayList;

public class NumeroVol {
    static int nbId = 1;
    public int id;
    public Vol vol;
    public ArrayList<Reservation> reservations;

    public NumeroVol(Vol vol) {
        id               = nbId++;
        this.vol         = vol;
        this.reservations = new ArrayList<>();
    }

    public void AjouterReservationAuVol(Reservation reservation) {
        reservations.add(reservation);
    }

    public void SupprimerReservationAuVol(Reservation reservation) {
        reservations.remove(reservation);
    }
}
