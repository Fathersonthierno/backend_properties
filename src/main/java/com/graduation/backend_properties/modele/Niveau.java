package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graduation.backend_properties.modele.Maison;
import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table
public class Niveau implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "maison_id")
    @JsonIgnoreProperties(value = {"niveaux", "property"})
    protected Maison maison ;

    protected int nbchambre ;
    protected int nbsalon ;
    protected int nbcuisineInt ;
    protected int nbcuisineExt ;
    protected int nbtoilette ;
    protected boolean couloir ;
    protected boolean magasin ;
    protected boolean eau ;
    protected boolean electricite ;
    protected   String nom;

    public Long getId() {
        return id;
    }

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }



}
