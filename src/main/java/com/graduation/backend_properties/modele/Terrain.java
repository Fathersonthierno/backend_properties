package com.graduation.backend_properties.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table
public class Terrain  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;

    @OneToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties(value = {"maison", "appart", "terrain", "operation", "owner", "operations"})
    protected Property property;

    protected String typeterrain;
    protected String dimension ;
    protected String typedocument;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getTypeterrain() {
        return typeterrain;
    }

    public void setTypeterrain(String typeterrain) {
        this.typeterrain = typeterrain;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getTypedocument() {
        return typedocument;
    }

    public void setTypedocument(String typedocument) {
        this.typedocument = typedocument;
    }

    public Long getId() {
        return id;
    }
}
