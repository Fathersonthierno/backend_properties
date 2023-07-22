package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Paiement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDate date;
    protected Double montant;
    protected String type;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    @JsonIgnoreProperties(value = {"user"})
    private Operation operation;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Long getId() {
        return id;
    }
}
