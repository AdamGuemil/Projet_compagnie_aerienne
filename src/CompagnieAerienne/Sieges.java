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


    //Modification de l'objet mere par defaut avec la methode toString() pour afficher les details du vol.
    @Override
    public String toString(){
        return "Numero de siege : " + numeroSiege + "et statut de la place : " + reserve;
    }
}
