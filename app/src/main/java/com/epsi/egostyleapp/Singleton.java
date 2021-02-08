package com.epsi.egostyleapp;

import androidx.core.util.Pair;

import java.util.ArrayList;


public class Singleton {

    private final static Singleton instance = new Singleton();

    public ArrayList<Bon> pairs_bons = new ArrayList<>();

    public String description_bon;
    public String date_limite_bon;
    public String codepromotion_bon;


    public final static Singleton getInstance() {
        return instance;
    }

    //retourne la liste
    public ArrayList<Bon> getPairList() {
        return pairs_bons;
    }

    //ajoute un bon à la liste
    public void setPairList(Bon bon) {
        if(!this.pairs_bons.contains(bon)){
            this.pairs_bons.add(bon);
        }
    }
}
