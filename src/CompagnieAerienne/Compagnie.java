package CompagnieAerienne;

public class Compagnie {
    private Vol[] listeVols;
    private Reservation[] listeReservations;
    private Avion[] listeAvions;
    private Passager[] listePassagers;
    private Sieges[] listeSieges;

    public String[] listeNationalitesPossibles = {"FRA","UK","USA"};



    public Compagnie() {
        listeVols         = new Vol[999];
        listeReservations = new Reservation[999];
        listeAvions       = new Avion[999];
        listePassagers    = new Passager[999];
    }

    public void AjouterAvion(int id, String modele,int capacite, int anneeService) {
        for (int i = 0; i < listeAvions.length; i++) {
            if (listeAvions[i].id == id) {
                System.out.println("pb, avion existe deja dans la liste");
                return;
            }
        }

        for (int i = 0; i < listeAvions.length; i++) {
            if (listeAvions[i] == null ) {
                listeAvions[i] = new Avion(id, listeSieges, modele,capacite,anneeService);
                System.out.println("avion bien ajoute à la liste");
                return;
            }
        }
    }

    public void SupprimerAvion(int id) {
        for (int i = 0; i < listeAvions.length; i++) {
            if (listeAvions[i].id == id ) {
                listeAvions[i] = null;
                System.out.println("avion bien supprimé de la liste");
                return;
            }
        }
    }

    public void AjouterVol(int id, NumeroVol numV) {
        // faire comme avion, mais pas oublier de créer numéroVol en meme temps
        for (int i = 0; i < listeVols.length; i++){
            if(listeVols[i].numeroVol.id == id) {
                listeVols[i] = null;
                //listeReservations[i] = new Reservation(listeVols, listeReservations);
                System.out.println("vol bien ajoute à la liste");
            }
        }
    }

    public void SupprimerVol(int id) {
        for (int i = 0; i < listeVols.length; i++) {
            if (listeVols[i].numeroVol.id == id ) {
                listeVols[i] = null;
                System.out.println("vol bien supprimé de la liste");
                return;
            }
        }
    }

    public boolean AjouterReservation(NumeroVol numVol,NumeroReservation numReservation) {
        for (int i = 0; i < listeReservations.length; i++) {
            if (listeReservations[i].numR == numReservation) {
                return false;
            }
        }

        for (int i = 0; i < listeReservations.length; i++) {
            if (listeReservations[i] == null ) {
                numVol.AjouterReservationAuVol(listeReservations[i]);
                listeReservations[i] = new Reservation(numVol, numReservation);
                return true;
            }
        }
        return false;
    }

    public boolean AnnulerReservation(NumeroReservation numReservation) {
        for (int i = 0; i < numReservation.p.reservationsClient.length; i++) {
            if (numReservation.p.reservationsClient[i] == numReservation){
                numReservation.p.reservationsClient[i] = null;
                break;
            }
        }

        for (int i = 0; i < numReservation.r.numV.reservations.length; i++) {
            if (numReservation.r.numV.reservations[i] == numReservation.r){
                numReservation.r.numV.reservations[i] = null;
                break;
            }
        }

        for (int i = 0; i < listeReservations.length; i++) {
            if (listeReservations[i].numR == numReservation){
                listeReservations[i] = null;
                break;
            }
        }
        return false;
    }

    public void AjouterPassager(String p,String n, String N,int nP) {

        for (int i = 0; i < listePassagers.length; i++) {
            if (listePassagers[i] == null ) {
                listePassagers[i] = new Passager(p,n,N,nP);
                return;
            }
        }
    }

    public Vol[] getListeVols(){
        return listeVols;
    }
    public Passager[] getListePassagers(){
        return listePassagers;
    }
    public Reservation[] getListeReservations(){
        return listeReservations;
    }
    public Avion[] getListeAvions(){
        return listeAvions;
    }

}
