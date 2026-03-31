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

    public static boolean CheckAvion(Avion[] listeAvions, int id){

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

    public static void afficherDetails(int id, int nbSieges, String modele, int capacite, int anneeService){
        System.out.println("Voici les details de l'avion :");
        System.out.println("1. Son ID est : " + id);
        System.out.println("2. Le nombre de sieges est : " + nbSieges);
        System.out.println("3. Le modele est : " + modele);
        System.out.println("4. Sa capacite est : " + capacite);
        System.out.println("1. Son annee de prise de service est : " + anneeService);
    }
}
