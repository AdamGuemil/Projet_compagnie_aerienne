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

    //Modification de l'objet mere par defaut avec la methode toString() pour afficher les details du vol.
    @Override
    public String toString(){
        return "Numero de vol : " + numeroVol + ", la ville de depart : " + villeDepart + ", la ville de destination : " + villeDestination + "et la date de depart " + dateDepart;
    }

}
