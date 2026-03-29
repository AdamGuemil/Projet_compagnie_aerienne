package CompagnieAerienne;

import java.io.SequenceInputStream;
import java.util.Objects;

public class Sieges {
    private int numeroSiege;
    private String classeSiege;
    private String equipements;
    int[][] premiereRangee = new int[20][4]; //Avion de 20 rangees de 4 sieges
    int[][] secondeRangee = new int[20][4]; //Idem pour la deuxieme rangee

    public Sieges(int numeroSiege, String classeSiege, String equipements){ // COnstructeur de la classe Siege
        this.numeroSiege = numeroSiege;
        this.classeSiege = classeSiege;
        this.equipements = equipements;
    }

    // Getter et setter pour la classe Sieges

    // Getter et setter pour numeroSiege
    public void setNumeroSiege(int numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public int getNumeroSiege() {
        return numeroSiege;
    }

    // Getter et setter pour classeSiege
    public void setClasseSiege(String classeSiege) {
        this.classeSiege = classeSiege;
    }

    public String getClasseSiege() {
        return classeSiege;
    }

    // Getter et setter equipements
    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }

    public String getEquipements() {
        return equipements;
    }

    // Getter et setter premiereRangee
    public void setPremiereRangee(int[][] premiereRangee) {
        this.premiereRangee = premiereRangee;
    }

    public int[][] getPremiereRangee() {
        return premiereRangee;
    }

    // Getter et setter secondeRangee
    public void setSecondeRangee(int[][] secondeRangee) {
        this.secondeRangee = secondeRangee;
    }

    public int[][] getSecondeRangee() {
        return secondeRangee;
    }

    public static String afficherDetails(int numeroSiege, String classeSiege, String equipements) { //Affiche les details sur un siege selectionee
        System.out.println("Voici les details du siege : ");
        return numeroSiege + classeSiege + equipements;
    }

    public static int reserverPlace(int numeroSiege, String classeSiege, String equipements, int[][] premiereRangee, int[][] secondeRangee){
        for(int i =0; i < premiereRangee.length && i < secondeRangee.length; i++){
            if(numeroSiege != 0 && Objects.equals(classeSiege, "Eco") && Objects.equals(equipements, "Table")){
               i++;
               premiereRangee[i][i] = 1; //On va incrementer chaque ligne et colonne par les nouveaux passagers ayant reserve une place
            }else{
                System.out.println("Caracteristique Siege invalide");
            }

            if(numeroSiege != 0 && Objects.equals(classeSiege, "First") && Objects.equals(equipements, "Ecran")){
                i++;
                premiereRangee[i][i] = 1;
            }else{
                System.out.println("Caracteristique Siege invalide");
            }

            if(numeroSiege != 0 && Objects.equals(classeSiege, "Premium") && Objects.equals(equipements, "Tout comfort")){
                i++;
            }else{
                System.out.println("Caracteristique Siege invalide");
            }
        }
        return 0;
    }

    public static int siegeLibre(int numeroSiege, int[][] premiereRangee, int[][] secondeRangee){
        if(numeroSiege == 0 && premiereRangee.equals(0) && secondeRangee.equals(0)){ // A reprendre je pense
            System.out.println("Siege libre");
        }else {
            System.out.println("Siege non libre");
        }
        return 0;
    }
}
