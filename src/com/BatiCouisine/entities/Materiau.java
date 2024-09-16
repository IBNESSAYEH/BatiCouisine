package com.BatiCouisine.entities;

public class Materiau {

    private double coutTransport;
    private double coefficientQualite;
    private double quantite;
    private double CoutUnitaire;

    public Materiau() {}

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
