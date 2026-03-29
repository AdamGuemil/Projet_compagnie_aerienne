package Commandes;
import CompagnieAerienne.*;

import java.util.Objects;
import java.util.Scanner;

public class Commande {

    public static String processCommand(String command, Compagnie comp){

        String[] parts = command.trim().split("\\s+");
        String key1 = parts[0].toLowerCase();

        switch (key1) {
            case "add":  return add(parts,comp);
            case "delete":  return delete(parts,comp);
            default:       return "Erreur : commande inconnue " + key1;
        }
    }

    public static String add(String[] parts, Compagnie comp){
        String key2 = parts[1].toLowerCase();

        switch (key2) {
            case "passager":  return comPassager(comp);
            case "vol":  return comVol(comp, "add");
            case "avion":  return comAvion(comp);
            case "reservation":  return comReservation(comp, "add");

            default:       return "Erreur : commande inconnue " + key2;
        }
    }

    public static String delete(String[] parts, Compagnie comp){
        String key2 = parts[1].toLowerCase();

        switch (key2) {
            case "passager":  return comPassager(comp);
            case "vol":  return comVol(comp, "delete");
            case "avion":  return comAvion(comp);
            case "reservation":  return comReservation(comp, "delete");

            default:       return "Erreur : commande inconnue " + key2;
        }
    }

    public static String comAvion(Compagnie comp){
        return "test";
    }

    public static String comReservation(Compagnie comp, String command){

        int param1;
        int param2;

        if (Objects.equals(command, "add")){


            do {
                System.out.println("Entrez le numéro de vol concerné par la reservation");
                Scanner sc = new Scanner(System.in);
                param1 = sc.nextInt();

                if (Vol.checkVol(comp.getListeVols(),param1)) {
                    break;
                }else{
                    System.out.println("Erreur : paramètre inconnu, veuillez donner un identifiant valide ");
                }
            } while(true);

            do {
                System.out.println("Entrez le numéro de passeport du passager concerné");
                Scanner sc = new Scanner(System.in);
                param2 = sc.nextInt();

                if (Passager.checkPassager(comp.getListePassagers(),param2)) {
                    break;
                }else{
                    System.out.println("Erreur : paramètre inconnu, veuillez donner un numéro de passeport valide, ou ajouter le passager dans la base de donnée s'il n'éxiste pas encore ");
                }
            } while(true);

            NumeroReservation numR = new NumeroReservation(Passager.getPassager(comp.getListePassagers(),param2), null);

            comp.AjouterReservation(Vol.getVol(comp.getListeVols(),param1).numeroVol,numR);
            return "Réservation créée";

        } else if (Objects.equals(command, "delete")){
            do {
                System.out.println("Entrez le numéro de réservation");
                Scanner sc = new Scanner(System.in);
                param1 = sc.nextInt();

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
            Scanner sc = new Scanner(System.in);
            param1 = sc.nextLine().trim();

            System.out.println("Entrez la ville d'arrivée");
            param2 = sc.nextLine().trim();

            do{
                System.out.println("Entrez l'id de l'avion associé");
                param3 = sc.nextInt();
                // TODO check si l'avion existe...
                break;
            }while (true);
            //comp.AjouterVol(int id); // TODO pas encore implémenté
            return "Vol créé";



        } else if (Objects.equals(command, "delete")){
            do{
                System.out.println("Entrez le numéro de vol");
                Scanner sc = new Scanner(System.in);
                param3 = sc.nextInt();
                // TODO check si le vol existe...
                break;
            }while (true);

            //comp.SupprimerVol(); // TODO pas encore implémenté
        }
        return "pb";
    }
    public static String comPassager(Compagnie comp){
        return "test";
    }
}
