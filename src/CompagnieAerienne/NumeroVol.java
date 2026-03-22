package CompagnieAerienne;

public class NumeroVol {
    static int nbId;
    int id;
    Vol vol;
    Reservation[] reservations;

    public NumeroVol(Vol vol){
        id = nbId++;
        this.vol = vol;
    }

    public void AjouterReservationAuVol(Reservation reservation){
        for (int i = 0; i<reservations.length;i++){
            if (reservations[i] == null){
                this.reservations[i] = reservation;

            }
        }
    }

    public void SupprimerReservationAuVol(Reservation reservation){
        for (int i = 0; i<reservations.length;i++){
            if (reservations[i] == reservation){
                this.reservations[i] = null;

            }
        }
    }
}
