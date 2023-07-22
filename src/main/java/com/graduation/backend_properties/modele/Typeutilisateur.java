package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;

@Entity
@Table
public class Typeutilisateur implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String type;
    protected String description;

    @OneToMany(mappedBy = "typeutilisateur")
    @JsonIgnoreProperties(value = {"operation","owner","operations", "typeutilisateur"})
    protected List<User> users;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
}
