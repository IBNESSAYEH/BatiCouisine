package com.BatiCouisine.entities;

import java.util.Date;

public class Devis {

    private int id;
    private double montantEstime;
    private Date dateMission;
    private Date dateValidite;
    private boolean isAccepted;

    public Devis() {}

    public Devis(int id, double montantEstime, Date dateMission, Date dateValidite, boolean isAccepted) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateMission = dateMission;
        this.dateValidite = dateValidite;
        this.isAccepted = isAccepted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public Date getDateMission() {
        return dateMission;
    }

    public void setDateMission(Date dateMission) {
        this.dateMission = dateMission;
    }

    public Date getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", dateMission=" + dateMission +
                ", dateValidite=" + dateValidite +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
