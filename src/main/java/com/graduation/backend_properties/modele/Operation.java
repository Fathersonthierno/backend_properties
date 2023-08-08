package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Operation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected Date datedebut ;
    protected Date datedfin ;
    protected Date datedemodification ;
    protected Date datecreation ;
    protected String typepaiement ;
    protected int prix ;
   protected String type ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"operation","owner","operations"})
    private User user;

//    @OneToOne
//    @JoinColumn(name = "property_ope_id")
//    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations"})
//    private Property bienimmobilier ;

    @OneToMany(mappedBy = "operation")
    private Set<Paiement> paiements = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations"})
    private Property property;

    public void addPaiement(Paiement paiement) {paiements.add(paiement);}
    public void removePaiement(Paiement paiement) {paiements.remove(paiement);}
    public Set<Paiement> getPaiements() {return paiements;}

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatedfin() {
        return datedfin;
    }

    public void setDatedfin(Date datedfin) {
        this.datedfin = datedfin;
    }

    public Date getDatedemodification() {
        return datedemodification;
    }

    public void setDatedemodification(Date datedemodification) {
        this.datedemodification = datedemodification;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getTypepaiement() {
        return typepaiement;
    }

    public void setTypepaiement(String typepaiement) {
        this.typepaiement = typepaiement;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
