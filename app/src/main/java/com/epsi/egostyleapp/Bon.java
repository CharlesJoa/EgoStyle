package com.epsi.egostyleapp;

public class Bon {
    private final String description;
    private final String date_limite;
    private final String promotion;
    private final String type;

    public Bon(String description, String date_limite, String promotion, String type) {
        this.description = description;
        this.date_limite = date_limite;
        this.promotion = promotion;
        this.type = type;
    }

    public String getdescription() { return description; }
    public String getdate_limite() { return date_limite; }
    public String getpromotion() { return promotion; }
    public String getType() { return type; }
}
