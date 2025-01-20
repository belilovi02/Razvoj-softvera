package org.example;

import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    private static int meni() {
        System.out.println("1. Unos novca u aparat");
        System.out.println("2. Vracanje novca u aparat");
        System.out.println("3. Unos nove opcij");
        System.out.println("4. Kupi kafu");
        System.out.println("5. Ispis ukupnih vrijednosti");
        System.out.println("6. Izlaz");
        int broj;
        do {
            System.out.println("Unesite zeljenu opciju");
            broj = s.nextInt();
            s.nextLine();
            if(broj <1 || broj >6){
                System.out.println("Nemoguca opcija, pokusajte ponovo");
            }
        }while(broj <1 || broj >6);

        return broj;
    }

    public static void main(String[] args) {
        boolean menu = true;

        while(true){
            int s = meni();

        }

    }
}