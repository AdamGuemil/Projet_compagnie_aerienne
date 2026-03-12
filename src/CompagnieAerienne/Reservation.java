package CompagnieAerienne;

public class Reservation {
    private Passager passager;
    //vol
    //SiegeReserve
    String dateReservation;
    boolean resaValidee;

    public Reservation(Passager passager){
        this.passager = passager;
    }

    public static Reservation Creer(Passager passager){
        return new Reservation(passager);
    }

    public static void Supprimer(Reservation[] reservations, Reservation reservation){
        // vol etcetc
        /*for (int i = 0; i<reservations.length; i++){
            if (reservations[i] == reservation) {
                reservations[i] == null;
            }
        }*/
        return;
    }
}
