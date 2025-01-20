package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CoffeTest {
    Coffe coffe;
    @Test
    public void getNazivIspravan() {
        String ocekivaniRezultat = "Kafa";
        assertEquals(ocekivaniRezultat, coffe.getName());
    }

    @Test
    public void setNazivIspravan() {
        String noviNaziv = "Test kafa";
        coffe.setName(noviNaziv);
        assertEquals(noviNaziv, coffe.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNazivNull() {
        String noviNaziv = null;
        coffe.setName(noviNaziv);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setNazivPrazanString() {
        String noviNaziv = "";
        coffe.setName(noviNaziv);
    }
}