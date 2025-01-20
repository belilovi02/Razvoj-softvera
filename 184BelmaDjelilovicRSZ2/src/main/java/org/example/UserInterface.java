package org.example;

import java.util.*;

public class UserInterface {
    private final Scanner scanner;
    private final crud<Proizvod> proizvodcrud;
    private boolean active;

    public UserInterface(Scanner scanner, crud<Proizvod> proizvodcrud) {
        this.scanner = scanner;
        this.proizvodcrud = proizvodcrud;
    }

    private void printMenu() {
        System.out.println("1. Dodaj proizvod");
        System.out.println("2. Ispis korpe");
        System.out.println("3. Izbrisi proizvod");
        System.out.println("0. Izlaz");
    }

    public void start() {
        active = true;
        while (active) {
            printMenu();
            performAction(scanner.nextInt());
        }
    }

    private void performAction(int option) {
        scanner.nextLine();
        try {
            switch (option) {
                case 1 -> proizvodcrud.create(dodajProizvod());
                case 2 -> {
                    proizvodcrud.readAll().forEach(System.out::println);
                }
                case 3 -> {
                    proizvodcrud.readAll().forEach(System.out::println);
                    System.out.print("Unesite ID proizvoda za obrisati: ");
                    proizvodcrud.deleteById(scanner.nextInt());
                }

                case 0 -> active = false;
                default -> System.out.println("Pogrešan izbor!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private Proizvod dodajProizvod() {
        String nazivP;
        do {
            System.out.print("Unesite naziv proizvoda: ");
            nazivP = scanner.nextLine();

            if(nazivP ==null) {
                System.out.println("Pogresan unos. ");
            }

        }while(nazivP == null);

        int cijenaP;
        do {
            System.out.print("Unesite cijenu proizvoda: ");
            cijenaP = scanner.nextInt();
            scanner.nextLine();

            if(cijenaP <=0) {
                System.out.println("Pogresan unos. ");
            }
        }while(cijenaP <= 0);

        return new Proizvod(nazivP, cijenaP);
    }

}
