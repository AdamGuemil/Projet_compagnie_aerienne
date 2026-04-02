package CompagnieAerienne;

import java.util.ArrayList;

public class Passager {
    private static int nbId = 1;

    public  int    id;
    public  String prenom;
    public  String nom;
    private int    numeroPasseport;
    private String nationalite;
    public  ArrayList<NumeroReservation> reservationsClient;

    public Passager(String prenom, String nom, String nationalite, int numeroPasseport) {
        this.id               = nbId++;
        this.prenom           = prenom;
        this.nom              = nom;
        this.nationalite      = nationalite;
        this.numeroPasseport  = numeroPasseport;
        this.reservationsClient = new ArrayList<>();
    }

    public int    getNumeroPasseport()          { return numeroPasseport; }
    public String getNationalite()              { return nationalite; }
    public void   setPrenom(String prenom)      { this.prenom = prenom; }
    public void   setNom(String nom)            { this.nom = nom; }
    public void   setNationalite(String n)      { this.nationalite = n; }

    public String afficherDetails() {
        return String.format("Passager [ID: %d | %s %s | Passeport: %d | Nationalité: %s | Réservations: %d]",
                id, prenom, nom, numeroPasseport, nationalite, reservationsClient.size());
    }

    public static boolean checkPassager(ArrayList<Passager> listePassagers, int passport) {
        for (Passager p : listePassagers) if (p.numeroPasseport == passport) return true;
        return false;
    }

    public static Passager getPassager(ArrayList<Passager> listePassagers, int passport) {
        for (Passager p : listePassagers) if (p.numeroPasseport == passport) return p;
        return null;
    }
}
