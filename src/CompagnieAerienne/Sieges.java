package CompagnieAerienne;

import java.io.SequenceInputStream;
import java.util.Objects;

public class Sieges {
    private int numeroSiege;
    private boolean reserve;

    public Sieges(int numeroSiege){ // COnstructeur de la classe Siege
        this.numeroSiege = numeroSiege;
    }

    // Getter et setter pour la classe Sieges

    // Getter et setter pour numeroSiege
    public void setNumeroSiege(int numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public int getNumeroSiege() {
        return numeroSiege;
    }

    public void reserveSiege() {
        reserve =!reserve;
    }


    public static String afficherDetails(int numeroSiege, String classeSiege, String equipements) { //Affiche les details sur un siege selectionee
        System.out.println("Voici les details du siege : ");
        return numeroSiege + classeSiege + equipements;
    }
}
