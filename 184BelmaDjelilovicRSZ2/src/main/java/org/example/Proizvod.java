package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Proizvod {
    private final String nazivProizvoda;
    private final Integer cijenaProizvoda;

    private final Integer ID;

    public Proizvod(int ID, Proizvod values) {
        this(ID, values.nazivProizvoda, values.cijenaProizvoda);
    }

    public Proizvod(String nazivProizvoda, int cijenaProizvoda) {
        this(-1, nazivProizvoda, cijenaProizvoda);
    }
    public Proizvod(int ID,String nazivProizvoda, int cijenaProizvoda){
        this.ID = ID;
        this.nazivProizvoda = nazivProizvoda;
        this.cijenaProizvoda = cijenaProizvoda;
    }

    public Integer getID() {
        return ID;
    }
    public String getNazivProizvoda() {
        return nazivProizvoda;
    }


    public Integer getCijenaProizvoda() {
        return cijenaProizvoda;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proizvod p = (Proizvod) o;
        return cijenaProizvoda == p.cijenaProizvoda &&
                Objects.equals(nazivProizvoda, p.nazivProizvoda);
    }

    @Override
    public String toString() {
        return "Naziv proizvoda: " + nazivProizvoda + " --> Cijena: " + cijenaProizvoda + " KM.";
    }
    public static void sortByPrice(List<Proizvod> items) {
        Collections.sort(items, new Comparator<Proizvod>() {
            public int compare(Proizvod o1, Proizvod o2) {
                return o1.getCijenaProizvoda().compareTo(o2.getCijenaProizvoda());
            }

            public boolean equals(Proizvod o1, Proizvod o2) {
                return o1.getCijenaProizvoda().equals(o2.getCijenaProizvoda());
            }

        });


    }}
