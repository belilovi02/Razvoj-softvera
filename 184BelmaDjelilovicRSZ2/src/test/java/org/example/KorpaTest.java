package org.example;

import junit.framework.TestCase;
import org.junit.Test;

public class KorpaTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }


    @Test(expected = NullPointerException.class)
    public void prosjekOcjenaWithNullList() {
        Proizvod student = new Proizvod(1, "", -1);
        student.toString();
    }
}
