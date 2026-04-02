package CompagnieAerienne;

import java.util.ArrayList;

public class Vol {
    public  NumeroVol numeroVol;
    private String    villeDepart;
    private String    villeDestination;
    private String    dateDepart;
    public  Avion     avion;

    public Vol(String villeDepart, String villeDestination, String dateDepart, Avion avion) {
        this.villeDepart       = villeDepart;
        this.villeDestination  = villeDestination;
        this.dateDepart        = dateDepart;
        this.avion             = avion;
        this.numeroVol         = new NumeroVol(this);
    }

    public String getVilleDepart()              { return villeDepart; }
    public String getVilleDestination()         { return villeDestination; }
    public String getDateDepart()               { return dateDepart; }
    public void   setVilleDepart(String v)      { villeDepart = v; }
    public void   setVilleDestination(String v) { villeDestination = v; }
    public void   setDateDepart(String d)       { dateDepart = d; }

    public int getPlacesDisponibles() {
        return avion.getSiegesDisponibles();
    }

    public String afficherDetails() {
        return String.format("Vol [N°: %d | %s → %s | Départ: %s | Avion: %s (ID %d) | Places: %d/%d]",
                numeroVol.id, villeDepart, villeDestination, dateDepart,
                avion.modele, avion.id, getPlacesDisponibles(), avion.capacite);
    }

    public static boolean checkVol(ArrayList<Vol> listeVols, int id) {
        for (Vol v : listeVols) if (v.numeroVol.id == id) return true;
        return false;
    }

    public static Vol getVol(ArrayList<Vol> listeVols, int id) {
        for (Vol v : listeVols) if (v.numeroVol.id == id) return v;
        return null;
    }
}
