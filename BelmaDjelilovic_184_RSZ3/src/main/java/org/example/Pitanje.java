package org.example;

import java.util.List;

public class Pitanje {
    public String timestamp;
    public String question;
    public List<Odgovor> odgovorList;
    public int question_num;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Odgovor> getAnswers() {
        return odgovorList;
    }

    public void setAnswers(List<Odgovor> odgovors) {
        this.odgovorList = odgovorList;
    }

    public int getQuestion_num() {
        return question_num;
    }

    public void setQuestion_num(int question_num) {
        this.question_num = question_num;
    }

    @Override
    public String toString() {
        String ispisPitanja = "";
        ispisPitanja += question + "\n";
        for(Odgovor a : odgovorList) ispisPitanja += a.toString();
        return ispisPitanja;
    }
}
