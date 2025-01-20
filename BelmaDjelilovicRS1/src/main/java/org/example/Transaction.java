package org.example;

import java.util.Objects;

public class Transaction {
    private final double price;
    private final Coffe coffe;

    public Transaction( Coffe coffe, double price) {
        this.price = price;
        this.coffe = coffe;
    }

    @Override
    public String toString() {
        return "Transaction\n" +
                "Name coffe=" + coffe +
                ", price=" + price +
                '\n';
    }

    public double getPrice() {
        return price;
    }

    public Coffe getCoffe() {
        return coffe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getCoffe().equals(that.getCoffe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoffe());
    }
}
