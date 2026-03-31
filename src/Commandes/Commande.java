package Commandes;
import CompagnieAerienne.*;

import java.util.Objects;
import java.util.Scanner;

import static CompagnieAerienne.Avion.CheckAvion;

class CommandeAnnuleeException extends RuntimeException {
    public CommandeAnnuleeException() {
        super("Opération annulée par l'utilisateur.");
    }
}

abstract public class Commande {

    public static String processCommand(String command, Compagnie comp){

        String[] parts = command.trim().split("\\s+");
        String key1 = parts[0].toLowerCase();

        try {
            switch (key1) {
                case "add":  return add(parts,comp);
                case "delete":  return delete(parts,comp);
                case "list":  return add(parts,comp);
                case "show":  return delete(parts,comp);
                case "modify":  return delete(parts,comp);
                default:       return "Erreur : commande inconnue " + key1;
            }
        } catch (CommandeAnnuleeException exception){
            return "Retour au menu";
        }
    }

    private static boolean checkExit(String command){

        if (Objects.equals(command.trim().toLowerCase(), "exit")){
            return true;
        }
        return false;
    }

    private static String getStringInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        if (checkExit(input)) throw new CommandeAnnuleeException();
        return input;
    }

    private static int getIntInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        if (checkExit(input)) throw new CommandeAnnuleeException();
        return Integer.parseInt(input);
    }

    public static String add(String[] parts, Compagnie comp){
        String key2 = parts[1].toLowerCase();

        switch (key2) {
            case "passager":  return comPassager(comp,"add");
            case "vol":  return comVol(comp, "add");
            case "avion":  return comAvion(comp, "add");
            case "reservation":  return comReservation(comp, "add");

            default:       return "Erreur : commande inconnue " + key2;
        }
    }


    public static String list(String[] parts, Compagnie comp){
        String key2 = parts[1].toLowerCase();


        switch (key2) {
            case "passagers":  return comPassager(comp,"list");
            case "vols":  return comVol(comp, "list");
            case "avions":  return comAvion(comp, "list");
            case "reservations":  return comReservation(comp, "list");

            default:       return "Erreur : commande inconnue " + key2;
        }
    }

    public static String show(String[] parts, Compagnie comp){
        String key2 = parts[1].toLowerCase();

        switch (key2) {
            case "passager": return comPassager(comp,"show");
            case "vol":  return comVol(comp, "show");
            case "avion":  return comAvion(comp, "show");
            case "reservation":  return comReservation(comp, "show");

            default:       return "Erreur : commande inconnue " + key2;
        }
    }


    public static String delete(String[] parts, Compagnie comp){
        String key2 = parts[1].toLowerCase();

        switch (key2) {
            case "passager":  return comPassager(comp,"delete");
            case "vol":  return comVol(comp, "delete");
            case "avion":  return comAvion(comp, "delete");
            case "reservation":  return comReservation(comp, "delete");

            default:       return "Erreur : commande inconnue " + key2;
        }
    }

    public static String comAvion(Compagnie comp, String command){
        String param1;
        int param2;
        int param3;

        if (Objects.equals(command, "add")){

            System.out.println("Entrez le modèle de l'avion");
            param1 = getStringInput();

            System.out.println("Entrez la capacité de l'avion");
            param2 = getIntInput();

            do{
                System.out.println("Entrez l'id de l'avion");
                param3 = getIntInput();
                if (Avion.CheckAvion(comp.getListeAvions(),param3)){
                    System.out.println("L'avion existe déjà, entrée un autre id ou annulez la commande");
                }else {
                    break;
                }
            }while (true);
            comp.AjouterAvion(param3,param1,param2);
            return "Vol créé";



        } else if (Objects.equals(command, "delete")){
            do{
                System.out.println("Entrez le numéro de vol");
                param3 = getIntInput();
                Vol.getVol(comp.getListeVols(),param3);
                break;
            }while (true);

            comp.SupprimerVol(param3);
        } else if (Objects.equals(command, "list")){
              System.out.println("====================");
              System.out.println("==Liste des avions==");
              System.out.println("====================");
              for (int i = 0;i<comp.getListeAvions().length;i++){
                  System.out.println(comp.getListeAvions()[i]);
              }

        } else if (Objects.equals(command, "show")){
            do{
                System.out.println("Entrez le numéro de vol");
                param3 = getIntInput();
                Vol.getVol(comp.getListeVols(),param3);
                break;
            }while (true);

            comp.SupprimerVol(param3);
        } else if (Objects.equals(command, "modify")){
            do{
                System.out.println("Entrez le numéro de vol");
                param3 = getIntInput();
                Vol.getVol(comp.getListeVols(),param3);
                break;
            }while (true);

            comp.SupprimerVol(param3);
        }
        return "pb";
    }

    public static String comReservation(Compagnie comp, String command){

        int param1;
        int param2;
        String param3;
        int param4;


        if (Objects.equals(command, "add")){
            do {
                System.out.println("Entrez le numéro de vol concerné par la reservation");
                param1 = getIntInput();

                if (Vol.checkVol(comp.getListeVols(),param1)) {
                    break;
                }else{
                    System.out.println("Erreur : paramètre inconnu, veuillez donner un identifiant valide ");
                }
            } while(true);

            do {
                System.out.println("Entrez le numéro de passeport du passager concerné");
                param2 = getIntInput();

                if (Passager.checkPassager(comp.getListePassagers(),param2)) {
                    break;
                }else{
                    System.out.println("Erreur : paramètre inconnu, veuillez donner un numéro de passeport valide, ou ajouter le passager dans la base de donnée s'il n'éxiste pas encore ");
                    System.out.println("Voulez-vous ajouter un passager miantenant ? (Y/N)");
                    param3 = getStringInput().toLowerCase();
                    if (param3.equals("y")){
                        comPassager(comp,"add");
                    }
                }
            } while(true);

            do {
                System.out.println("Entrez le numéro de siège à reserver");
                //TODO print sieges dispo de l'avion du vol
                param4 = getIntInput();
                break;
            } while(true);

            Sieges siege = Vol.getVol(comp.getListeVols(),param1).avion.listeSieges[param4];
            NumeroReservation numR = new NumeroReservation(Passager.getPassager(comp.getListePassagers(),param2), null);
            comp.AjouterReservation(Vol.getVol(comp.getListeVols(),param1).numeroVol,numR,siege);
            return "Réservation créée";

        } else if (Objects.equals(command, "delete")){
            do {
                System.out.println("Entrez le numéro de réservation");
                param1 = getIntInput();

                if (Reservation.CheckReservation(comp.getListeReservations(),param1)) {
                    break;
                }else{
                    System.out.println("Erreur : réservation inconnue, veuillez donner un identifiant valide ");
                }
            } while(true);

            comp.AnnulerReservation(Reservation.getNumeroReservation(comp.getListeReservations(),param1));
            return "Réservation annulée";

        }
        return "pb";
    }

    public static String comVol(Compagnie comp, String command){
        String param1;
        String param2;
        int param3;

        if (Objects.equals(command, "add")){

            System.out.println("Entrez la ville de départ");
            param1 = getStringInput();

            System.out.println("Entrez la ville d'arrivée");
            param2 = getStringInput();

            do{
                System.out.println("Entrez l'id de l'avion associé");
                param3 = getIntInput();
                if (Avion.CheckAvion(comp.getListeAvions(),param3)){
                    break;
                }else {
                    System.out.println("L'identifiant entré est invalide");
                }
            }while (true);

            comp.AjouterVol(param1,param2,0101,Avion.getAvionFromId(comp.getListeAvions(),param3));
            return "Vol créé";

        } else if (Objects.equals(command, "delete")){
            do{
                System.out.println("Entrez le numéro de vol");
                param3 = getIntInput();
                Vol.getVol(comp.getListeVols(),param3);
                break;
            }while (true);

            comp.SupprimerVol(param3);
        }
        return "pb";
    }
    public static String comPassager(Compagnie comp, String command){
        int param1;
        String param2;
        String param3;
        String param4;

        if (Objects.equals(command, "add")){


            do {
                System.out.println("Entrez le numéro de passeport du Passager à ajouter dans la liste de donnée");
                param1 = getIntInput();

                if (Passager.checkPassager(comp.getListePassagers(),param1)) {
                    System.out.println("Erreur : Ce numéro de passeport est déjà enregistré.");
                }else{
                    break;
                }
            } while(true);

                System.out.println("Entrez le prénom du passager");
                param2 = getStringInput();

                System.out.println("Entrez le nom du passager");
                param3 = getStringInput();

            do {

                System.out.println("Entrez la nationalité du passager");
                param4 = getStringInput();

                //check natio...
                break;
            } while(true);



            comp.AjouterPassager(param2,param3,param4,param1);
            return "Passager enregistré";

        } else if (Objects.equals(command, "delete")){
            do {
                System.out.println("Entrez le numéro de réservation");
                param1 = getIntInput();

                if (Reservation.CheckReservation(comp.getListeReservations(),param1)) {
                    break;
                }else{
                    System.out.println("Erreur : réservation inconnue, veuillez donner un identifiant valide ");
                }
            } while(true);

            comp.AnnulerReservation(Reservation.getNumeroReservation(comp.getListeReservations(),param1));
            return "Réservation annulée";

        }
        return "pb";
    }
}
