package CompagnieAerienne;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    public NumeroReservation numR;
    public NumeroVol         numV;
    public Sieges            siegeReserve;
    public String            dateReservation;
    public boolean           active;

    public Reservation(NumeroVol numV, NumeroReservation numR, Sieges siegeReserve) {
        numR.r = this;
        this.numR  = numR;
        this.numV  = numV;
        siegeReserve.reserveSiege();
        this.siegeReserve = siegeReserve;
        this.dateReservation = LocalDate.now().toString();
        this.active = true;
        numR.p.reservationsClient.add(numR);
    }

    public String afficherDetails() {
        return String.format(
            "Réservation [N°: %d | Vol: %d | Passager: %s %s | Siège: %d | Date: %s | Statut: %s]",
            numR.id, numV.id,
            numR.p.prenom, numR.p.nom,
            siegeReserve.getNumeroSiege(),
            dateReservation,
            active ? "Active" : "Annulée"
        );
    }

    public static boolean CheckReservation(ArrayList<Reservation> listeReservations, int id) {
        for (Reservation r : listeReservations) if (r.numR.id == id) return true;
        return false;
    }

    public static Reservation getReservation(ArrayList<Reservation> listeReservations, int id) {
        for (Reservation r : listeReservations) if (r.numR.id == id) return r;
        return null;
    }

    public static NumeroReservation getNumeroReservation(ArrayList<Reservation> listeReservations, int id) {
        for (Reservation r : listeReservations) if (r.numR.id == id) return r.numR;
        return null;
    }
}
