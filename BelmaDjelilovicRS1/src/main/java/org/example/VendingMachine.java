package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VendingMachine<T> {
    protected final Map<String, T> options = new HashMap<>();

    Collection<String> getAvailableProducts(){
        return options.keySet();
    };
    void addOption(String name, T option){
        if(options.equals(option)) throw new IllegalArgumentException("opcija vec postoji");
    options.put(name, option);
    };
    public abstract T buy(String option);
    public abstract void refill();
}
