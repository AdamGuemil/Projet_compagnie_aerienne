package Commandes;

import CompagnieAerienne.*;

import java.util.Scanner;

class CommandeAnnuleeException extends RuntimeException {
    public CommandeAnnuleeException() { super("Opération annulée par l'utilisateur."); }
}

abstract public class Commande {

    private static final Scanner sc = new Scanner(System.in);

    public static String processCommand(String command, Compagnie comp) {
        String[] parts = command.trim().split("\\s+");
        if (parts.length == 0 || parts[0].isEmpty()) return "";

        String key1 = parts[0].toLowerCase();
        try {
            switch (key1) {
                case "add":    return add(parts, comp);
                case "delete": return delete(parts, comp);
                case "list":   return list(parts, comp);
                case "show":   return show(parts, comp);
                case "modify": return modify(parts, comp);
                case "help":   return showHelp();
                default:       return "Commande inconnue : « " + key1 + " »  –  tapez help pour l'aide.";
            }
        } catch (CommandeAnnuleeException e) {
            return "\n↩  Opération annulée – retour au menu.";
        }
    }

    public static String showHelp() {
        return """
                ╔══════════════════════════════════════════════════════╗
                ║              COMMANDES DISPONIBLES                  ║
                ╠══════════════════════════════════════════════════════╣
                ║  add    passager / vol / avion / reservation        ║
                ║  delete passager / vol / avion / reservation        ║
                ║  modify passager / vol / avion                      ║
                ║  list   passagers / vols / avions / reservations    ║
                ║  show   passager / vol / avion / reservation        ║
                ║  help                                               ║
                ╠══════════════════════════════════════════════════════╣
                ║  Tapez « exit » à tout moment pour annuler          ║
                ╚══════════════════════════════════════════════════════╝""";
    }

    private static String getStringInput(String prompt) {
        System.out.print("  » " + prompt + " : ");
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) throw new CommandeAnnuleeException();
        return input;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print("  » " + prompt + " : ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) throw new CommandeAnnuleeException();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Entrez un nombre entier valide.");
            }
        }
    }

    private static int getOptionalIntInput(String prompt) {
        System.out.print("  » " + prompt + " : ");
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) throw new CommandeAnnuleeException();
        if (input.isEmpty()) return -1;
        try { return Integer.parseInt(input); } catch (NumberFormatException e) { return -1; }
    }

    public static String add(String[] parts, Compagnie comp) {
        if (parts.length < 2) return "Usage : add <passager|vol|avion|reservation>";
        switch (parts[1].toLowerCase()) {
            case "passager":    return comPassager(comp, "add");
            case "vol":         return comVol(comp, "add");
            case "avion":       return comAvion(comp, "add");
            case "reservation": return comReservation(comp, "add");
            default:            return "Type inconnu : « " + parts[1] + " »";
        }
    }

    public static String delete(String[] parts, Compagnie comp) {
        if (parts.length < 2) return "Usage : delete <passager|vol|avion|reservation>";
        switch (parts[1].toLowerCase()) {
            case "passager":    return comPassager(comp, "delete");
            case "vol":         return comVol(comp, "delete");
            case "avion":       return comAvion(comp, "delete");
            case "reservation": return comReservation(comp, "delete");
            default:            return "Type inconnu : « " + parts[1] + " »";
        }
    }

    public static String list(String[] parts, Compagnie comp) {
        if (parts.length < 2) return "Usage : list <passagers|vols|avions|reservations>";
        switch (parts[1].toLowerCase()) {
            case "passagers":    return comPassager(comp, "list");
            case "vols":         return comVol(comp, "list");
            case "avions":       return comAvion(comp, "list");
            case "reservations": return comReservation(comp, "list");
            default:             return "Type inconnu : « " + parts[1] + " »";
        }
    }

    public static String show(String[] parts, Compagnie comp) {
        if (parts.length < 2) return "Usage : show <passager|vol|avion|reservation>";
        switch (parts[1].toLowerCase()) {
            case "passager":    return comPassager(comp, "show");
            case "vol":         return comVol(comp, "show");
            case "avion":       return comAvion(comp, "show");
            case "reservation": return comReservation(comp, "show");
            default:            return "Type inconnu : « " + parts[1] + " »";
        }
    }

    public static String modify(String[] parts, Compagnie comp) {
        if (parts.length < 2) return "Usage : modify <passager|vol|avion>";
        switch (parts[1].toLowerCase()) {
            case "passager": return comPassager(comp, "modify");
            case "vol":      return comVol(comp, "modify");
            case "avion":    return comAvion(comp, "modify");
            default:         return "Type inconnu : « " + parts[1] + " »";
        }
    }

    public static String comAvion(Compagnie comp, String command) {
        switch (command) {

            case "add": {
                System.out.println("\n── Ajout d'un avion ──────────────────────");
                String modele    = getStringInput("Modèle");
                int    capacite  = getIntInput("Capacité (nbre de sièges)");
                int    annee     = getIntInput("Année de mise en service");
                int    id;
                do {
                    id = getIntInput("ID de l'avion");
                    if (!Avion.CheckAvion(comp.getListeAvions(), id)) break;
                    System.out.println("  ⚠  Cet ID est déjà utilisé.");
                } while (true);
                comp.AjouterAvion(id, modele, capacite, annee);
                return "✔  Avion ajouté (ID " + id + ").";
            }

            case "delete": {
                System.out.println("\n── Suppression d'un avion ────────────────");
                if (comp.getListeAvions().isEmpty()) return "Aucun avion enregistré.";
                int id;
                do {
                    id = getIntInput("ID de l'avion à supprimer");
                    if (Avion.CheckAvion(comp.getListeAvions(), id)) break;
                    System.out.println("  ⚠  Avion introuvable.");
                } while (true);
                return comp.SupprimerAvion(id) ? "✔  Avion supprimé." : "✘  Suppression impossible.";
            }

            case "modify": {
                System.out.println("\n── Modification d'un avion ───────────────");
                if (comp.getListeAvions().isEmpty()) return "Aucun avion enregistré.";
                int id;
                do {
                    id = getIntInput("ID de l'avion à modifier");
                    if (Avion.CheckAvion(comp.getListeAvions(), id)) break;
                    System.out.println("  ⚠  Avion introuvable.");
                } while (true);
                Avion a = Avion.getAvionFromId(comp.getListeAvions(), id);
                System.out.println("  (Entrée vide = conserver la valeur actuelle)");
                String modele    = getStringInput("Modèle       [actuel : " + a.modele + "]");
                int    capacite  = getOptionalIntInput("Capacité     [actuel : " + a.capacite + "]");
                int    annee     = getOptionalIntInput("Année service[actuel : " + a.anneeService + "]");
                comp.ModifierAvion(id,
                        modele.isEmpty()    ? null : modele,
                        capacite,
                        annee);
                return "✔  Avion modifié.";
            }

            case "list": {
                if (comp.getListeAvions().isEmpty()) return "Aucun avion enregistré.";
                StringBuilder sb = new StringBuilder("\n═══════════════════════════════\n  Liste des avions\n═══════════════════════════════\n");
                for (Avion a : comp.getListeAvions()) sb.append("  ").append(a.afficherDetails()).append("\n");
                return sb.toString();
            }

            case "show": {
                System.out.println("\n── Détails d'un avion ────────────────────");
                if (comp.getListeAvions().isEmpty()) return "Aucun avion enregistré.";
                int id;
                do {
                    id = getIntInput("ID de l'avion");
                    if (Avion.CheckAvion(comp.getListeAvions(), id)) break;
                    System.out.println("  ⚠  Avion introuvable.");
                } while (true);
                Avion a = Avion.getAvionFromId(comp.getListeAvions(), id);
                StringBuilder sb = new StringBuilder("\n  " + a.afficherDetails() + "\n  Sièges :\n");
                for (Sieges s : a.listeSieges) sb.append("    ").append(s).append("\n");
                return sb.toString();
            }
        }
        return "Commande avion inconnue.";
    }

    public static String comVol(Compagnie comp, String command) {
        switch (command) {

            case "add": {
                System.out.println("\n── Ajout d'un vol ────────────────────────");
                if (comp.getListeAvions().isEmpty())
                    return "✘  Aucun avion disponible – ajoutez d'abord un avion.";
                String vd = getStringInput("Ville de départ");
                String va = getStringInput("Ville de destination");
                String dd = getStringInput("Date et heure de départ (ex: 12/07/2025 14:30)");
                System.out.println("  Avions disponibles :");
                for (Avion a : comp.getListeAvions()) System.out.println("    " + a.afficherDetails());
                int avionId;
                do {
                    avionId = getIntInput("ID de l'avion associé");
                    if (Avion.CheckAvion(comp.getListeAvions(), avionId)) break;
                    System.out.println("  ⚠  Avion introuvable.");
                } while (true);
                comp.AjouterVol(vd, va, dd, Avion.getAvionFromId(comp.getListeAvions(), avionId));
                return "✔  Vol créé.";
            }

            case "delete": {
                System.out.println("\n── Suppression d'un vol ──────────────────");
                if (comp.getListeVols().isEmpty()) return "Aucun vol enregistré.";
                int id;
                do {
                    id = getIntInput("Numéro du vol à supprimer");
                    if (Vol.checkVol(comp.getListeVols(), id)) break;
                    System.out.println("  ⚠  Vol introuvable.");
                } while (true);
                return comp.SupprimerVol(id) ? "✔  Vol supprimé (réservations associées annulées)." : "✘  Suppression impossible.";
            }

            case "modify": {
                System.out.println("\n── Modification d'un vol ─────────────────");
                if (comp.getListeVols().isEmpty()) return "Aucun vol enregistré.";
                int id;
                do {
                    id = getIntInput("Numéro du vol à modifier");
                    if (Vol.checkVol(comp.getListeVols(), id)) break;
                    System.out.println("  ⚠  Vol introuvable.");
                } while (true);
                Vol v = Vol.getVol(comp.getListeVols(), id);
                System.out.println("  (Entrée vide = conserver la valeur actuelle)");
                String vd = getStringInput("Ville de départ      [actuel : " + v.getVilleDepart() + "]");
                String va = getStringInput("Ville de destination [actuel : " + v.getVilleDestination() + "]");
                String dd = getStringInput("Date de départ       [actuel : " + v.getDateDepart() + "]");
                comp.ModifierVol(id,
                        vd.isEmpty() ? null : vd,
                        va.isEmpty() ? null : va,
                        dd.isEmpty() ? null : dd);
                return "✔  Vol modifié.";
            }

            case "list": {
                if (comp.getListeVols().isEmpty()) return "Aucun vol enregistré.";
                StringBuilder sb = new StringBuilder("\n═══════════════════════════════\n  Liste des vols\n═══════════════════════════════\n");
                for (Vol v : comp.getListeVols()) sb.append("  ").append(v.afficherDetails()).append("\n");
                return sb.toString();
            }

            case "show": {
                System.out.println("\n── Détails d'un vol ──────────────────────");
                if (comp.getListeVols().isEmpty()) return "Aucun vol enregistré.";
                int id;
                do {
                    id = getIntInput("Numéro du vol");
                    if (Vol.checkVol(comp.getListeVols(), id)) break;
                    System.out.println("  ⚠  Vol introuvable.");
                } while (true);
                Vol v = Vol.getVol(comp.getListeVols(), id);
                StringBuilder sb = new StringBuilder("\n  " + v.afficherDetails() + "\n  Passagers embarqués :\n");
                if (v.numeroVol.reservations.isEmpty()) {
                    sb.append("    (aucun)\n");
                } else {
                    for (Reservation r : v.numeroVol.reservations) {
                        sb.append(String.format("    - %s %s  |  Siège %d  |  Résa N°%d%n",
                                r.numR.p.prenom, r.numR.p.nom,
                                r.siegeReserve.getNumeroSiege(), r.numR.id));
                    }
                }
                return sb.toString();
            }
        }
        return "Commande vol inconnue.";
    }

    public static String comPassager(Compagnie comp, String command) {
        switch (command) {

            case "add": {
                System.out.println("\n── Ajout d'un passager ───────────────────");
                int passport;
                do {
                    passport = getIntInput("Numéro de passeport");
                    if (!Passager.checkPassager(comp.getListePassagers(), passport)) break;
                    System.out.println("  ⚠  Ce numéro de passeport est déjà enregistré.");
                } while (true);
                String prenom = getStringInput("Prénom");
                String nom    = getStringInput("Nom");
                String natio  = getStringInput("Nationalité");
                comp.AjouterPassager(prenom, nom, natio, passport);
                return "✔  Passager enregistré.";
            }

            case "delete": {
                System.out.println("\n── Suppression d'un passager ─────────────");
                if (comp.getListePassagers().isEmpty()) return "Aucun passager enregistré.";
                int passport;
                do {
                    passport = getIntInput("Numéro de passeport du passager à supprimer");
                    if (Passager.checkPassager(comp.getListePassagers(), passport)) break;
                    System.out.println("  ⚠  Passager introuvable.");
                } while (true);
                return comp.SupprimerPassager(passport)
                        ? "✔  Passager supprimé (réservations associées annulées)."
                        : "✘  Suppression impossible.";
            }

            case "modify": {
                System.out.println("\n── Modification d'un passager ────────────");
                if (comp.getListePassagers().isEmpty()) return "Aucun passager enregistré.";
                int passport;
                do {
                    passport = getIntInput("Numéro de passeport du passager");
                    if (Passager.checkPassager(comp.getListePassagers(), passport)) break;
                    System.out.println("  ⚠  Passager introuvable.");
                } while (true);
                Passager p = Passager.getPassager(comp.getListePassagers(), passport);
                System.out.println("  (Entrée vide = conserver la valeur actuelle)");
                String prenom = getStringInput("Prénom      [actuel : " + p.prenom + "]");
                String nom    = getStringInput("Nom         [actuel : " + p.nom + "]");
                String natio  = getStringInput("Nationalité [actuel : " + p.getNationalite() + "]");
                comp.ModifierPassager(passport,
                        prenom.isEmpty() ? null : prenom,
                        nom.isEmpty()    ? null : nom,
                        natio.isEmpty()  ? null : natio);
                return "✔  Passager modifié.";
            }

            case "list": {
                if (comp.getListePassagers().isEmpty()) return "Aucun passager enregistré.";
                StringBuilder sb = new StringBuilder("\n═══════════════════════════════\n  Liste des passagers\n═══════════════════════════════\n");
                for (Passager p : comp.getListePassagers()) sb.append("  ").append(p.afficherDetails()).append("\n");
                return sb.toString();
            }

            case "show": {
                System.out.println("\n── Détails d'un passager ─────────────────");
                if (comp.getListePassagers().isEmpty()) return "Aucun passager enregistré.";
                int passport;
                do {
                    passport = getIntInput("Numéro de passeport");
                    if (Passager.checkPassager(comp.getListePassagers(), passport)) break;
                    System.out.println("  ⚠  Passager introuvable.");
                } while (true);
                Passager p = Passager.getPassager(comp.getListePassagers(), passport);
                StringBuilder sb = new StringBuilder("\n  " + p.afficherDetails() + "\n  Réservations :\n");
                if (p.reservationsClient.isEmpty()) {
                    sb.append("    (aucune)\n");
                } else {
                    for (NumeroReservation nr : p.reservationsClient) {
                        if (nr.r != null) sb.append("    ").append(nr.r.afficherDetails()).append("\n");
                    }
                }
                return sb.toString();
            }
        }
        return "Commande passager inconnue.";
    }

    public static String comReservation(Compagnie comp, String command) {
        switch (command) {

            case "add": {
                System.out.println("\n── Nouvelle réservation ──────────────────");
                if (comp.getListeVols().isEmpty())      return "✘  Aucun vol disponible.";
                if (comp.getListePassagers().isEmpty()) return "✘  Aucun passager enregistré.";

                Vol vol;
                do {
                    int volId = getIntInput("Numéro du vol");
                    vol = Vol.getVol(comp.getListeVols(), volId);
                    if (vol == null)                     { System.out.println("  ⚠  Vol introuvable."); continue; }
                    if (vol.getPlacesDisponibles() == 0) { System.out.println("  ⚠  Ce vol est complet."); vol = null; continue; }
                    break;
                } while (true);

                int passport;
                do {
                    passport = getIntInput("Numéro de passeport du passager");
                    if (Passager.checkPassager(comp.getListePassagers(), passport)) break;
                    String rep = getStringInput("  Passager introuvable. Créer maintenant ? (o/n)");
                    if (rep.equalsIgnoreCase("o")) comPassager(comp, "add");
                } while (!Passager.checkPassager(comp.getListePassagers(), passport));

                System.out.println("  Sièges disponibles pour ce vol :");
                for (Sieges s : vol.avion.listeSieges) {
                    if (!s.isReserved()) System.out.println("    " + s);
                }
                Sieges siegeChoisi;
                do {
                    int siegeNum = getIntInput("Numéro du siège à réserver");
                    if (siegeNum < 1 || siegeNum > vol.avion.listeSieges.length) {
                        System.out.println("  ⚠  Numéro de siège invalide (1–" + vol.avion.listeSieges.length + ").");
                        continue;
                    }
                    siegeChoisi = vol.avion.listeSieges[siegeNum - 1];
                    if (siegeChoisi.isReserved()) { System.out.println("  ⚠  Ce siège est déjà pris."); continue; }
                    break;
                } while (true);

                Passager p    = Passager.getPassager(comp.getListePassagers(), passport);
                NumeroReservation numR = new NumeroReservation(p, null);
                boolean ok = comp.AjouterReservation(vol.numeroVol, numR, siegeChoisi);
                return ok ? "✔  Réservation créée (N°" + numR.id + ")." : "✘  Erreur lors de la réservation.";
            }

            case "delete": {
                System.out.println("\n── Annulation d'une réservation ──────────");
                if (comp.getListeReservations().isEmpty()) return "Aucune réservation enregistrée.";
                int id;
                do {
                    id = getIntInput("Numéro de réservation à annuler");
                    if (Reservation.CheckReservation(comp.getListeReservations(), id)) break;
                    System.out.println("  ⚠  Réservation introuvable.");
                } while (true);
                NumeroReservation nr = Reservation.getNumeroReservation(comp.getListeReservations(), id);
                return comp.AnnulerReservation(nr) ? "✔  Réservation annulée." : "✘  Erreur.";
            }

            case "list": {
                if (comp.getListeReservations().isEmpty()) return "Aucune réservation enregistrée.";
                StringBuilder sb = new StringBuilder("\n═══════════════════════════════\n  Liste des réservations\n═══════════════════════════════\n");
                for (Reservation r : comp.getListeReservations()) sb.append("  ").append(r.afficherDetails()).append("\n");
                return sb.toString();
            }

            case "show": {
                System.out.println("\n── Détails d'une réservation ─────────────");
                if (comp.getListeReservations().isEmpty()) return "Aucune réservation enregistrée.";
                int id;
                do {
                    id = getIntInput("Numéro de réservation");
                    if (Reservation.CheckReservation(comp.getListeReservations(), id)) break;
                    System.out.println("  ⚠  Réservation introuvable.");
                } while (true);
                return "\n  " + Reservation.getReservation(comp.getListeReservations(), id).afficherDetails();
            }
        }
        return "Commande réservation inconnue.";
    }
}
