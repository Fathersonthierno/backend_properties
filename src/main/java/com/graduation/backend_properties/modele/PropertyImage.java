package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * A Attachment.
 */
@Entity
@Table
public class PropertyImage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    protected Long id;

    @Lob
    @Column(name = "piece")
    private byte[] piece;

    @Column(name = "name")
    private String name;

    @Column(name = "piece_content_type")
    private String pieceContentType;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations" , "images"})
    private Property property;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropertyImage id(Long id) {
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPiece() {
        return this.piece;
    }


    public PropertyImage piece(byte[] piece) {
        this.setPiece(piece);
        return this;
    }

    public void setPiece(byte[] piece) {
        this.piece = piece;
    }

    public String getPieceContentType() {
        return this.pieceContentType;
    }

    public PropertyImage pieceContentType(String pieceContentType) {
        this.pieceContentType = pieceContentType;
        return this;
    }

    public void setPieceContentType(String pieceContentType) {
        this.pieceContentType = pieceContentType;
    }

    public String getDescription() {
        return this.description;
    }

    public PropertyImage description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public PropertyImage date(Date date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
