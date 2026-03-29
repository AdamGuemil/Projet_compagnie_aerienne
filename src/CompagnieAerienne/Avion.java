package CompagnieAerienne;

public class Avion {
    public int id;
    public Sieges[] nbSieges;
    public String modele;
    public int capacite;
    public int anneeService;

    public Avion(int id, Sieges[] nbSieges, String modele, int capacite, int anneeService){
        this.id = id;
        this.nbSieges = nbSieges;
        this.modele = modele;
        this.capacite = capacite;
        this.anneeService = anneeService;
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
