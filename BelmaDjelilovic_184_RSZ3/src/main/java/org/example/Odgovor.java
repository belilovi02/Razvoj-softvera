package org.example;

public class Odgovor {
    public String text;
    public boolean correct;
    public String choice;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        String ispisOdgovora = "";
        if (choice.equals("A")) ispisOdgovora += "1) ";
        else if (choice.equals("B")) ispisOdgovora += "2) ";
        else ispisOdgovora += "3) ";
        ispisOdgovora += text + "\n";
        return ispisOdgovora;
    }
}
