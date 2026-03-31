package CompagnieAerienne;

import java.util.List;
import java.util.Scanner;

public class Vol {
    static int nbVols;
    public NumeroVol numeroVol;
    private String villeDepart;
    private String villeDestination;
    private double dateDepart;
    public Avion avion;
    public Reservation reservation;

    public Vol(String villeDepart, String villeDestination, double dateDepart, Avion avion){
        this.villeDepart = villeDepart;
        this.villeDestination= villeDestination;
        this.dateDepart = dateDepart;
        this.avion = avion;
        this.numeroVol = new NumeroVol(this);
    }


    public static String afficherDetails(int numeroVol,  String villeDepart, String villeDestination, double dateDepart,Avion avion) {
        System.out.println("Voici les details du vol : ");
        return numeroVol +  villeDepart + villeDestination + dateDepart + avion;
    }

    public static boolean checkVol(Vol[] listeVols, int id){ // regarde si vol existe
        for (int i=0;i<listeVols.length;i++){
            if (listeVols[i] != null && listeVols[i].numeroVol.id == id){
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
