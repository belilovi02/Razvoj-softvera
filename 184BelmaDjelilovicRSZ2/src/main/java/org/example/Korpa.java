package org.example;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Korpa implements crud<Proizvod> {
    private int counter = 1;
    private final Map<Integer, Proizvod> proizvodMap = new HashMap<>();

    @Override
    public void create(Proizvod value) {
        proizvodMap.put(counter, new Proizvod(counter, value));
        counter++;
    }

    @Override
    public List<Proizvod> readAll() {
        return proizvodMap.values().stream().collect(Collectors.toList());
    }


    @Override
    public void deleteById(int id) {
        proizvodMap.remove(id);
    }


}
