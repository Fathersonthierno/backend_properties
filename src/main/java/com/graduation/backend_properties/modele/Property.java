package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Property implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne(mappedBy = "property",cascade = CascadeType.PERSIST)
    protected Maison maison;

    @OneToOne(mappedBy = "property", cascade = CascadeType.PERSIST)
    protected Appart appart;

    @OneToOne(mappedBy = "property", cascade = CascadeType.PERSIST)
    protected Terrain terrain;

    @Column(name = "adresse")
    protected String adresse;
    @Column(name = "description")
    protected String description;
    @Column(name = "type")
    protected String type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    protected PropertyOwner owner;

    @OneToMany(mappedBy = "property")
    @JsonIgnoreProperties(value = {"user",  "paiements", "property"})
    protected Set<Operation> operations = new HashSet<>();
    public void addOperation(Operation operation) {
        operations.add(operation);
    }
    public void removeOperation(Operation operation) {
        operations.remove(operation);
    }
    public Set<Operation> getOperations() {
        return operations;
    }


    @OneToMany(mappedBy = "property")
    @JsonIgnoreProperties(value = { "property"})
    protected Set<PropertyImage> images = new HashSet<>();

    public void addImage(PropertyImage image) {
        images.add(image);
    }

    public void removeImage(PropertyImage image) {
        images.remove(image);
    }

    public Set<PropertyImage> getImages() {
        return images;
    }

    public PropertyOwner getOwner() {
        return owner;
    }

    public void setOwner(PropertyOwner owner) {
        this.owner = owner;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Appart getAppart() {
        return appart;
    }

    public void setAppart(Appart appart) {
        this.appart = appart;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

