package CompagnieAerienne;

public class Vol {
    NumeroVol numeroVol;
    private int numeroDepart;
    private String villeDepart;
    private String villeDestination;
    private double dateDepart;
    private int placesDisponibles;
    private Avion avion;

    public Vol(NumeroVol numeroVol, int numeroDepart, String villeDepart, String villeDestination, double dateDepart, int placesDisponibles, Avion avion){
        this.numeroVol = numeroVol;
        this.numeroDepart = numeroDepart;
        this.villeDepart = villeDepart;
        this.villeDestination= villeDestination;
        this.dateDepart = dateDepart;
        this.placesDisponibles = placesDisponibles;
        this.avion = avion;
    }


    public static String afficherDetails(int numeroVol, int numeroDepart, String villeDepart, String villeDestination, double dateDepart,Avion avion) {
        System.out.println("Voici les details du vol : ");
        return numeroVol + numeroDepart + villeDepart + villeDestination + dateDepart + avion;
    }

    public static boolean reserverPlace(){

        int placesDisponibles;
        return true;
    }

}
