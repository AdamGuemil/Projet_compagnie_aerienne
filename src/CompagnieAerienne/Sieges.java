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

    public static String afficherDetails(int numeroSiege, String classeSiege, String equipements) { //Affiche les details sur un siege selectionee
        System.out.println("Voici les details du siege : ");
        return numeroSiege + classeSiege + equipements;
    }

    public static int reserverPlace(int numeroSiege, String classeSiege, String equipements, int[][] premiereRangee, int[][] secondeRangee){
        for(int i =0; i < premiereRangee.length && i < secondeRangee.length; i++){
            if(numeroSiege != 0 && Objects.equals(classeSiege, "Eco") && Objects.equals(equipements, "Table")){
               i++;
               premiereRangee[i][i] = 1; //On va incremente chaque ligne et colonne par les nouveaux passagers ayant reserve une place
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
}
