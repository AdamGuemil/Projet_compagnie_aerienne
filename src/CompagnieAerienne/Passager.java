package CompagnieAerienne;

public class Passager {
    protected String prenom;
    protected String nom;
    private int numeroPasseport;
    private String nationalite;
    public  NumeroReservation[] reservationsClient;

    public Passager(String p,String n, String N,int nP){
        prenom = p;
        nom = n;
        nationalite = N;
        numeroPasseport = nP;
    }

    public static boolean checkPassager(Passager[] listePassagers, int id){ // regarde si passager existe
        for (int i=0;i<listePassagers.length;i++){
            if (listePassagers[i] !=null && listePassagers[i].numeroPasseport == id){
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

    @Override
    public String toString(){
        return "Prenom : " + prenom + ", nom : " + nom + ", numero de passeport : " + numeroPasseport + ", nationalite : " + nationalite + "et la reservation client : " + reservationsClient;
    }

}
