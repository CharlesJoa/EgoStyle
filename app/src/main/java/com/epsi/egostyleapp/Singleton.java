package com.epsi.egostyleapp;

import androidx.core.util.Pair;

import java.util.ArrayList;


public class Singleton {

    private final static Singleton instance = new Singleton();

    public ArrayList<Pair<String, String>> pairs_bons = new ArrayList<>();

    public String description_bon;
    public String date_limite_bon;
    public String codepromotion_bon;


    public final static Singleton getInstance() {
        return instance;
    }

    //retourne la liste
    public ArrayList<Pair<String, String>> getPairList() {
        return pairs_bons;
    }

    //ajoute un bon à la liste
    public void setPairList(Pair<String, String> pair) {
        if(!this.pairs_bons.contains(pair)){
            this.pairs_bons.add(pair);
        }
    }
}
