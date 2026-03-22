package CompagnieAerienne;

public class Vol {
    static int nbVols;
    public NumeroVol numeroVol;
    private String villeDepart;
    private String villeDestination;
    private double dateDepart;
    private int placesDisponibles;
    private Avion avion;

    public Vol(NumeroVol numeroVol,  String villeDepart, String villeDestination, double dateDepart, Avion avion){
        this.numeroVol = numeroVol;
        this.villeDepart = villeDepart;
        this.villeDestination= villeDestination;
        this.dateDepart = dateDepart;
        this.placesDisponibles = avion.nbSieges;
        this.avion = avion;
    }


    public static String afficherDetails(int numeroVol,  String villeDepart, String villeDestination, double dateDepart,Avion avion) {
        System.out.println("Voici les details du vol : ");
        return numeroVol +  villeDepart + villeDestination + dateDepart + avion;
    }

    public static boolean reserverPlace(){

        int placesDisponibles;
        return true;
    }

    public static boolean checkVol(Vol[] listeVols, int id){ // regarde si vol existe
        for (int i=0;i<listeVols.length;i++){
            if (listeVols[i].numeroVol.id == id){
                return true;
            }
        }
        return false;
    }

    public static Vol getVol(Vol[] listeVols, int id){ // récupère vol
        for (int i=0;i<listeVols.length;i++){
            if (listeVols[i].numeroVol.id == id){
                return listeVols[i];
            }
        }
        return null;
    }

}
