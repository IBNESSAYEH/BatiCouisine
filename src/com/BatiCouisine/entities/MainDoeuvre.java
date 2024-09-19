package com.BatiCouisine.entities;

public class MainDoeuvre extends Composant {

    private double tauxHoraire;
    private double heurTravail;
    private double productiviteOuvrier;


public MainDoeuvre() {}
    public MainDoeuvre(int id, String nom, String typeComposant, Double tauxTVA, double tauxHoraire, double heurTravail, double productiviteOuvrier) {
        super(id, nom, typeComposant, tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heurTravail = heurTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeurTravail() {
        return heurTravail;
    }

    public void setHeurTravail(double heurTravail) {
        this.heurTravail = heurTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "MainDoeuvre{" +
                "tauxHoraire=" + tauxHoraire +
                ", heurTravail=" + heurTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                '}';
    }
}
