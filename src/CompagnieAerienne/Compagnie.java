package CompagnieAerienne;

import java.util.ArrayList;

public class Compagnie {
    private ArrayList<Vol>         listeVols;
    private ArrayList<Reservation> listeReservations;
    private ArrayList<Avion>       listeAvions;
    private ArrayList<Passager>    listePassagers;

    public Compagnie() {
        listeVols         = new ArrayList<>();
        listeReservations = new ArrayList<>();
        listeAvions       = new ArrayList<>();
        listePassagers    = new ArrayList<>();
    }

    public boolean AjouterAvion(int id, String modele, int capacite, int anneeService) {
        if (Avion.CheckAvion(listeAvions, id)) {
            System.out.println("Erreur : un avion avec cet ID existe déjà.");
            return false;
        }
        listeAvions.add(new Avion(id, modele, capacite, anneeService));
        return true;
    }

    public boolean SupprimerAvion(int id) {
        Avion a = Avion.getAvionFromId(listeAvions, id);
        if (a == null) { System.out.println("Avion introuvable."); return false; }
        for (Vol v : listeVols) {
            if (v.avion == a) {
                System.out.println("Erreur : cet avion est assigné au vol N°" + v.numeroVol.id + ".");
                return false;
            }
        }
        listeAvions.remove(a);
        return true;
    }

    public boolean ModifierAvion(int id, String modele, int capacite, int anneeService) {
        Avion a = Avion.getAvionFromId(listeAvions, id);
        if (a == null) { System.out.println("Avion introuvable."); return false; }
        if (modele      != null) a.modele       = modele;
        if (capacite     > 0)   a.capacite      = capacite;
        if (anneeService > 0)   a.anneeService  = anneeService;
        return true;
    }

    public boolean AjouterVol(String villeDepart, String villeDestination, String dateDepart, Avion avion) {
        listeVols.add(new Vol(villeDepart, villeDestination, dateDepart, avion));
        return true;
    }

    public boolean SupprimerVol(int id) {
        Vol v = Vol.getVol(listeVols, id);
        if (v == null) { System.out.println("Vol introuvable."); return false; }
        ArrayList<Reservation> aAnnuler = new ArrayList<>();
        for (Reservation r : listeReservations) {
            if (r.numV == v.numeroVol) aAnnuler.add(r);
        }
        for (Reservation r : aAnnuler) AnnulerReservation(r.numR);
        listeVols.remove(v);
        return true;
    }

    public boolean ModifierVol(int id, String villeDepart, String villeDestination, String dateDepart) {
        Vol v = Vol.getVol(listeVols, id);
        if (v == null) { System.out.println("Vol introuvable."); return false; }
        if (villeDepart       != null) v.setVilleDepart(villeDepart);
        if (villeDestination  != null) v.setVilleDestination(villeDestination);
        if (dateDepart        != null) v.setDateDepart(dateDepart);
        return true;
    }

    public boolean AjouterPassager(String prenom, String nom, String nationalite, int numeroPasseport) {
        if (Passager.checkPassager(listePassagers, numeroPasseport)) {
            System.out.println("Erreur : numéro de passeport déjà enregistré.");
            return false;
        }
        listePassagers.add(new Passager(prenom, nom, nationalite, numeroPasseport));
        return true;
    }

    public boolean SupprimerPassager(int passport) {
        Passager p = Passager.getPassager(listePassagers, passport);
        if (p == null) { System.out.println("Passager introuvable."); return false; }
        ArrayList<NumeroReservation> aAnnuler = new ArrayList<>(p.reservationsClient);
        for (NumeroReservation nr : aAnnuler) AnnulerReservation(nr);
        listePassagers.remove(p);
        return true;
    }

    public boolean ModifierPassager(int passport, String prenom, String nom, String nationalite) {
        Passager p = Passager.getPassager(listePassagers, passport);
        if (p == null) { System.out.println("Passager introuvable."); return false; }
        if (prenom      != null) p.setPrenom(prenom);
        if (nom         != null) p.setNom(nom);
        if (nationalite != null) p.setNationalite(nationalite);
        return true;
    }

    public boolean AjouterReservation(NumeroVol numVol, NumeroReservation numReservation, Sieges siegeReserve) {
        if (siegeReserve.isReserved()) {
            System.out.println("Erreur : ce siège est déjà réservé.");
            return false;
        }
        if (numVol.vol.getPlacesDisponibles() == 0) {
            System.out.println("Erreur : le vol est complet.");
            return false;
        }
        Reservation r = new Reservation(numVol, numReservation, siegeReserve);
        listeReservations.add(r);
        numVol.AjouterReservationAuVol(r);
        return true;
    }

    public boolean AnnulerReservation(NumeroReservation numReservation) {
        if (numReservation == null || numReservation.r == null) return false;
        Reservation resa = numReservation.r;
        resa.siegeReserve.libereSiege();
        resa.active = false;
        numReservation.p.reservationsClient.remove(numReservation);
        resa.numV.SupprimerReservationAuVol(resa);
        listeReservations.remove(resa);
        return true;
    }

    public ArrayList<Vol>         getListeVols()          { return listeVols; }
    public ArrayList<Passager>    getListePassagers()     { return listePassagers; }
    public ArrayList<Reservation> getListeReservations()  { return listeReservations; }
    public ArrayList<Avion>       getListeAvions()        { return listeAvions; }
}
