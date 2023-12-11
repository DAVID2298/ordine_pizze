package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Input del cliente ( scanner )
        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nella nostra pizzeria!");

        System.out.print("Hai una fidelity card? (true/false): ");
        boolean FidelityCard = scanner.nextBoolean();

        System.out.print("Sei diversamente abile? (true/false): ");
        boolean diversamenteAbile = scanner.nextBoolean();

        System.out.print("Inserisci il numero di persone nel tuo gruppo: ");
        int numeroPersone = scanner.nextInt();

        System.out.print("Inserisci l'orario dell'ordine: ");
        int orarioOrdine = scanner.nextInt();

        System.out.print("È weekend? (true/false): ");
        boolean isWeekend = scanner.nextBoolean();

        System.out.print("Inserisci l'età del cliente: ");
        int eta = scanner.nextInt();

        // Calcola il prezzo finale
        double prezzoBasePizza = 20.0;
        double prezzoFinale = calcolaSconto(prezzoBasePizza, FidelityCard, diversamenteAbile,
                numeroPersone, orarioOrdine, isWeekend, eta);

        System.out.println("Il prezzo base finale è: " + prezzoFinale + "€");

        scanner.close();
    }

    // Metodo per calcolare sconto in base a

    public static double calcolaSconto(double prezzoBase, boolean FidelityCard, boolean diversamenteAbile,
                                           int numeroPersone, int orarioOrdine, boolean isWeekend, int eta) {
        double sconto = 0.0;
        double scontoGruppi = 0.0;

        // Sconto per chi ha la fidelity card
        if (FidelityCard) {
            sconto += 0.15;
        }
        // Sconto per diversamente abili
        if (diversamenteAbile) {
            sconto += 0.9;
        }
        // Sconto per gruppi
        else if (numeroPersone >= 15 && numeroPersone <= 20) {
            scontoGruppi += 0.2;
        } else if (numeroPersone >= 21 && numeroPersone <= 25) {
            scontoGruppi += 0.3;
        } else if (numeroPersone > 25) {
            scontoGruppi += 0.5;
        }
        // Sconto per orario
        if (sconto == 0.0 && scontoGruppi == 0.0 && ((orarioOrdine < 20 && !isWeekend) || isWeekend)) {
            sconto += 0.1;
        }
        // Sconto per anziani
        if (eta >= 60) {
            sconto += 0.7;
        }
        // Sconto per bambini
        else if (scontoGruppi == 0.0 && eta < 4) {
            sconto += 0.5;
        } else if (scontoGruppi == 0.0 && eta < 12) {
            sconto += 0.2;
        }


        double prezzoScontato = prezzoBase * (1 - (sconto + scontoGruppi));
        double prezzoFinale = Math.max(prezzoScontato, 5.0);

        return prezzoFinale;
    }
}

