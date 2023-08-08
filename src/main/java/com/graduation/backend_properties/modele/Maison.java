package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table
public class  Maison implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations"})
    protected Property property ;

    @OneToMany(mappedBy = "maison")
    protected Set<Niveau> niveaux = new HashSet<>();
    public void addNiveau(Niveau niveau) {
        niveaux.add(niveau);
    }
    public void removeNiveau(Niveau niveau) {
        niveaux.remove(niveau);
    }
    public Set<Niveau> getNiveaux() {
        return niveaux;
    }

    protected String dimension ;
    protected String typeDocment;
    protected boolean garage ;
    protected int capaciteGarage ;
    protected boolean cour ;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getTypeDocment() {
        return typeDocment;
    }

    public void setTypeDocment(String typeDocment) {
        this.typeDocment = typeDocment;
    }

    public boolean getGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public int getCapaciteGarage() {
        return capaciteGarage;
    }

    public void setCapaciteGarage(int capaciteGarage) {
        this.capaciteGarage = capaciteGarage;
    }

    public boolean getCour() {
        return cour;
    }

    public void setCour(boolean cour) {
        this.cour = cour;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Long getId() {
        return id;
    }
}
