import CompagnieAerienne.*;
import Commandes.*;

import java.util.Scanner;

void main() {
    Compagnie comp = new Compagnie();
    Scanner sc = new Scanner(System.in);

    System.out.println("╔══════════════════════════════════════════════════════╗");
    System.out.println("║      Système de Gestion de Compagnie Aérienne       ║");
    System.out.println("╚══════════════════════════════════════════════════════╝");
    System.out.println(Commande.showHelp());

    while (true) {
        System.out.println("\n──────────────────────────────────────────────────────");
        System.out.print("Commande > ");
        String command = sc.nextLine().trim();
        if (command.isEmpty()) continue;
        String result = Commande.processCommand(command, comp);
        System.out.println(result);
    }
}
