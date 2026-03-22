import CompagnieAerienne.*;
import Commandes.*;


void main() {

    Compagnie comp = new Compagnie();
    do {
        System.out.println("==============================================");
        System.out.println("==Bienvenue dans le terminal de la compagnie==");
        System.out.println("==============================================");
        System.out.println(" ");

        System.out.println("Entrez une commande, ou entrez \"help\" pour de l'aide.");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Commande.processCommand(command, comp);

    }while(true);
}
