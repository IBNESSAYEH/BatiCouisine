package com.BatiCouisine.entities;

public class Materiau extends Composant {

    private double coutTransport;
    private double coefficientQualite;
    private double quantite;
    private double CoutUnitaire;
    public Materiau() {}
    public Materiau(int id, String nom, String typeComposant, Double tauxTVA, double coutTransport, double coefficientQualite, double quantite, double coutUnitaire) {
        super(id, nom, typeComposant, tauxTVA);
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
        this.quantite = quantite;
        CoutUnitaire = coutUnitaire;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutUnitaire() {
        return CoutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        CoutUnitaire = coutUnitaire;
    }

    @Override
    public String toString() {
        return "Materiau{" +
                "coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                ", quantite=" + quantite +
                ", CoutUnitaire=" + CoutUnitaire +
                '}';
    }
}
