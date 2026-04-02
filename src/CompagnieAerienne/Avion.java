package CompagnieAerienne;

import java.util.ArrayList;

public class Avion {
    public int    id;
    public String modele;
    public int    capacite;
    public int    anneeService;
    public Sieges[] listeSieges;

    public Avion(int id, String modele, int capacite,  int anneeService) {
        this.id           = id;
        this.modele       = modele;
        this.capacite     = capacite;
        this.anneeService = anneeService;
        listeSieges = new Sieges[capacite];
        for (int i = 0; i < capacite; i++) {
            listeSieges[i] = new Sieges(i + 1);
        }
    }

    public int getSiegesDisponibles() {
        int count = 0;
        for (Sieges s : listeSieges) if (!s.isReserved()) count++;
        return count;
    }

    public String afficherDetails() {
        return String.format("Avion [ID: %d | Modèle: %s | Capacité: %d | Mise en service: %d | Dispo: %d/%d]",
                id, modele, capacite, anneeService, getSiegesDisponibles(), capacite);
    }

    public static boolean CheckAvion(ArrayList<Avion> listeAvions, int id) {
        for (Avion a : listeAvions) if (a.id == id) return true;
        return false;
    }

    public static Avion getAvionFromId(ArrayList<Avion> listeAvions, int id) {
        for (Avion a : listeAvions) if (a.id == id) return a;
        return null;
    }
}
