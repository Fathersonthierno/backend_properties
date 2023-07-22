package com.graduation.backend_properties.repository;

import com.graduation.backend_properties.modele.Property;
import com.graduation.backend_properties.modele.PropertyOwner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class Propertyownercontroller {
    private final PropertyownerRepository propertyownerRepository;
    private final PropertyRepository propertyRepository;


    public Propertyownercontroller(PropertyownerRepository propertyownerRepository, PropertyRepository propertyRepository) {
        this.propertyownerRepository = propertyownerRepository;
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public ResponseEntity<List<PropertyOwner>> getOwners(){
        return ResponseEntity.ok(propertyownerRepository.findAll());
    }



    @PostMapping
    public PropertyOwner addpropertyowner(@RequestBody PropertyOwner propertyOwner) {
        return propertyownerRepository.save(propertyOwner);
    }
    @PostMapping("/properties")
    public Property addPropertyToOwner(@RequestBody Property property) {
//        PropertyOwner owner = propertyownerRepository.findById(ownerId)
//                .orElseThrow(() -> new IllegalArgumentException("Propriétaire non trouvé"));
//
//        if (!owner.isAuthorizedToAddProperty()) {
//            throw new UnauthorizedAccessException("Vous n'êtes pas autorisé à ajouter un bien immobilier");
//        }

        property.setOwner(null);

        propertyRepository.save(property);
//        propertyownerRepository.save(owner);

        return property;
    }

    @DeleteMapping("/properties/{propertyId}")
    public void deleteProperty(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Bien immobilier non trouvé"));

        PropertyOwner owner = property.getOwner();
        if (!owner.isAuthorizedToDeleteProperty()) {
            throw new UnauthorizedAccessException("Vous n'êtes pas autorisé à supprimer ce bien immobilier");
        }
        propertyRepository.delete(property);
    }

    @PutMapping("/properties/{propertyId}")
    public Property updateProperty(@PathVariable Long propertyId, @RequestBody Property updatedProperty) {
        Property existingProperty = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Bien immobilier non trouvé"));

        PropertyOwner owner = existingProperty.getOwner();
        if (!owner.isAuthorizedToUpdateProperty()) {
            throw new UnauthorizedAccessException("Vous n'êtes pas autorisé à mettre à jour ce bien immobilier");
        }
        propertyRepository.save(existingProperty);

        return existingProperty;
    }

}

//
