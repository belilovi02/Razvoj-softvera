package org.example;

import java.util.Objects;

public class Coffe {
    private String name;
    private Integer requiredMilk, requiredCoffe, requiredWater;
    private double price;
public Coffe(){
    Coffe c = new Coffe();
}

    public Coffe(String name, Integer requiredMilk, Integer requiredCoffe, Integer requiredWater, double price) {
        this.name = name;
        this.requiredMilk = requiredMilk;
        this.requiredCoffe = requiredCoffe;
        this.requiredWater = requiredWater;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffe" +
                "name:'" + name + '\'' +
                ", requiredMilk:" + requiredMilk +
                ", requiredCoffe:" + requiredCoffe +
                ", requiredWater:" + requiredWater +
                ", price:" + price +
                '.';
    }

    public String getName() {
        return name;
    }

    public Integer getRequiredMilk() {
        return requiredMilk;
    }

    public Integer getRequiredCoffe() {
        return requiredCoffe;
    }

    public Integer getRequiredWater() {
        return requiredWater;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffe)) return false;
        Coffe coffe = (Coffe) o;
        return getName().equals(coffe.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public void setName(String name) {
        if(name == null) throw new IllegalArgumentException("Nemoguca opcija");
        this.name = name;
    }

    public void setRequiredMilk(Integer requiredMilk) {
        this.requiredMilk = requiredMilk;
    }

    public void setRequiredCoffe(Integer requiredCoffe) {
        this.requiredCoffe = requiredCoffe;
    }

    public void setRequiredWater(Integer requiredWater) {
        this.requiredWater = requiredWater;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
