package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Pristupite vasoj korpi");
        System.out.println("0. Kraj");
        crud<Proizvod> proizvodi = null;
        while (proizvodi == null) proizvodi = switch (scanner.nextInt()) {
            case 1 -> new Korpa();
            default -> null;
        };
        UserInterface ui = new UserInterface(scanner, proizvodi);
        ui.start();
    }
    }
