package com.BatiCouisine.entities;

public class Project {
    private int id;
    private String nom;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProject etat;
    private double surfaceCouisine;

    public Project() {}

    public Project(int id, String nom, double margeBeneficiaire, double coutTotal, EtatProject etat, double surfaceCouisine) {
        this.id = id;
        this.nom = nom;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etat = etat;
        this.surfaceCouisine = surfaceCouisine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProject getEtat() {
        return etat;
    }

    public void setEtat(EtatProject etat) {
        this.etat = etat;
    }

    public double getSurfaceCouisine() {
        return surfaceCouisine;
    }

    public void setSurfaceCouisine(double surfaceCouisine) {
        this.surfaceCouisine = surfaceCouisine;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", coutTotal=" + coutTotal +
                ", etat=" + etat +
                ", surfaceCouisine=" + surfaceCouisine +
                '}';
    }
}
