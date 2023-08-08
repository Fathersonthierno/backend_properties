package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table
public class Appart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @OneToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations"})
    protected Property property ;
    protected int nbchambre ;
    protected Integer nbsalon ;
    protected int nbcuisineInt ;
    protected boolean eauSeparee ;
    protected Boolean electriciteSeparee ;

    protected String extra ;
    protected int nbcuisineExt ;
    protected int nbtoilette ;
    protected boolean couloir ;
    protected boolean magasin ;
    protected boolean eau ;
    protected boolean electricite ;

    public int getNbchambre() {
        return nbchambre;
    }

    public void setNbchambre(int nbchambre) {
        this.nbchambre = nbchambre;
    }

    public int getNbsalon() {
        return nbsalon;
    }

    public void setNbsalon(int nbsalon) {
        this.nbsalon = nbsalon;
    }

    public int getNbcuisineInt() {
        return nbcuisineInt;
    }

    public void setNbcuisineInt(int nbcuisineInt) {
        this.nbcuisineInt = nbcuisineInt;
    }

    public int getNbcuisineExt() {
        return nbcuisineExt;
    }

    public void setNbcuisineExt(int nbcuisineExt) {
        this.nbcuisineExt = nbcuisineExt;
    }

    public int getNbtoilette() {
        return nbtoilette;
    }

    public void setNbtoilette(int nbtoilette) {
        this.nbtoilette = nbtoilette;
    }

    public boolean getCouloir() {
        return couloir;
    }

    public void setCouloir(boolean couloir) {
        this.couloir = couloir;
    }

    public boolean getMagasin() {
        return magasin;
    }

    public void setMagasin(boolean magasin) {
        this.magasin = magasin;
    }

    public boolean getEau() {
        return eau;
    }

    public void setEau(boolean eau) {
        this.eau = eau;
    }

    public boolean getElectricite() {
        return electricite;
    }

    public void setElectricite(boolean electricite) {
        this.electricite = electricite;
    }

    public Long getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public boolean isEauSeparee() {
        return eauSeparee;
    }

    public void setEauSeparee(boolean eauSeparee) {
        this.eauSeparee = eauSeparee;
    }

    public boolean isElectriciteSeparee() {
        return electriciteSeparee;
    }

    public void setElectriciteSeparee(boolean electriciteSeparee) {
        this.electriciteSeparee = electriciteSeparee;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
