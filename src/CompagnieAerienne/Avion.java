package CompagnieAerienne;

import java.io.Serial;

public class Avion {
    public int id;
    public Sieges[] listeSieges;
    public String modele;
    public int capacite;

    public Avion(int id, String modele, int capacite){
        this.id = id;
        this.modele = modele;
        this.capacite = capacite;
        listeSieges = new Sieges[capacite];
        int num =0;
        for (int i = 0; i < listeSieges.length; i++) {
            listeSieges[i] = new Sieges(num++);
        }
    }

    public static boolean checkAvion(Avion[] listeAvions, int id){

        for (int i=0;i<listeAvions.length;i++){
            if (listeAvions[i]!=null && listeAvions[i].id == id){
                return true;
            }
        }
        return false;
    }

    public static Avion getAvionFromId(Avion[] listeAvions, int id){
        for (int i=0;i<listeAvions.length;i++){
            if (listeAvions[i].id == id){
                return listeAvions[i];
            }
        }
        return null;
    }

    //Modification de l'objet mere par defaut avec la methode toString() pour afficher les details de l'avion.
    @Override
    public String toString(){
        return "ID du vol : " + id + ", les sieges occupes : " + listeSieges + ", le modele de l'avion : " + modele + "et sa capacite totale : " +capacite;
    }
}
