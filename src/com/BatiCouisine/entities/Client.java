package com.BatiCouisine.entities;

public class Client {
    private int id;
    private String nom;
    private String numeroTelephone;
    private String address;
    private boolean estProfessionnelle;


    public Client() {}
    public Client(int id, String nom, String numeroTelephone, String address, boolean estProfessionnelle) {
        this.id = id;
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.address = address;
        this.estProfessionnelle = estProfessionnelle;
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

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isProfessionnel() {
        return estProfessionnelle;
    }

    public void setEstProfessionnelle(boolean estProfessionnelle) {
        this.estProfessionnelle = estProfessionnelle;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", address='" + address + '\'' +
                ", estProfessionnelle=" + estProfessionnelle +
                '}';
    }
}
