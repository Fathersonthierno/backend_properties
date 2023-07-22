package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property_owner")
public class PropertyOwner implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String code;

    @Column(name = "owner_nb_bien")
    protected int nbbien;

    @Column(name = "owner_pieceidentite")
    protected String pieceidentite;

    @Column(name = "owner_justificatif")
    protected String justificatifs;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"operation","owner","operations"})
    protected User user;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations"})
    protected Set<Property> properties = new HashSet<>();

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public int getNbbien() {
        return nbbien;
    }

    public void setNbbien(int nbbien) {
        this.nbbien = nbbien;
    }

    public String getPieceidentite() {
        return pieceidentite;
    }

    public void setPieceidentite(String pieceidentite) {
        this.pieceidentite = pieceidentite;
    }

    public String getJustificatifs() {
        return justificatifs;
    }

    public void setJustificatifs(String justificatifs) {
        this.justificatifs = justificatifs;
    }

    public boolean isAuthorizedToUpdateProperty() {
        return true;
    }

    public boolean isAuthorizedToAddProperty() {
        return true;
    }

    public boolean isAuthorizedToDeleteProperty() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
