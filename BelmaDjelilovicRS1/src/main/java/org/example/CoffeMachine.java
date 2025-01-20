package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeMachine extends VendingMachine<Coffe> implements CoinOperated {
    private double money = 0;
    private int milkAmount = 0;
    private int coffeAmount = 0;
    private int waterAmount = 0;
    Scanner s = new Scanner(System.in);
    private List<Transaction> transactions= new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public Coffe buy(String option) {
        if (!options.containsKey(option)) throw new IllegalArgumentException("Opcija nije dostupna");
        Coffe coffe = options.get(option);
        if (money < coffe.getPrice()) throw new IllegalArgumentException("Niste ubacili dovoljno novca");
        if(milkAmount<coffe.getRequiredMilk() || coffeAmount<coffe.getRequiredCoffe() || waterAmount<coffe.getRequiredWater()) throw new IllegalArgumentException("Potrebno je napuniti aparat");
        transactions.add(new Transaction(coffe, coffe.getPrice()));
        return coffe;
    }

    @Override
    public void refill() {
        this.money = 1000;
       this. milkAmount = 1000;
        this.coffeAmount = 1000;
        this.waterAmount = 1000;
    }

    @Override
    public void addMoney(double value) throws Exception {
        System.out.println("Unesite novac");
        value = s.nextDouble();
        s.nextLine();
    }

    @Override
    public double refund() {
        Coffe cofee = new Coffe();
        return this.money-=cofee.getPrice();
    }

    public void setMoney(double money) {
        if(money<0) throw new IllegalArgumentException("Nemoguc unos novca");
        this.money = money;
    }

    public void setMilkAmount(int milkAmount) {
        if(milkAmount<0) throw new IllegalArgumentException("Nemoguc unos");
        this.milkAmount = milkAmount;
    }

    public void setCoffeAmount(int coffeAmount) {
        if(coffeAmount<0) throw new IllegalArgumentException("Nemoguc unos ");

        this.coffeAmount = coffeAmount;
    }

    public void setWaterAmount(int waterAmount) {
        if(waterAmount<0) throw new IllegalArgumentException("Nemoguc unos");
        this.waterAmount = waterAmount;
    }
}
