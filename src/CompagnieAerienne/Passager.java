package CompagnieAerienne;

public class Passager {
    protected String prenom;
    protected String nom;
    private int numeroPasseport;
    private String Nationalite;
    public  NumeroReservation[] reservationsClient;

    public String afficherDetails(){
        return "a";
    }

    public static boolean checkPassager(Passager[] listePassagers, int id){ // regarde si passager existe
        for (int i=0;i<listePassagers.length;i++){
            if (listePassagers[i].numeroPasseport == id){
                return true;
            }
        }
        return false;
    }

    public static Passager getPassager(Passager[] listePassagers, int id){
        for (int i=0;i<listePassagers.length;i++){
            if (listePassagers[i].numeroPasseport == id){
                return listePassagers[i];
            }
        }
        return null;
    }

}
