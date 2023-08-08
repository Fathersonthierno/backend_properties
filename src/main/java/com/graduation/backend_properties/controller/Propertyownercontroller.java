package com.graduation.backend_properties.controller;

import com.graduation.backend_properties.modele.Property;
import com.graduation.backend_properties.modele.PropertyOwner;
import com.graduation.backend_properties.modele.User;
import com.graduation.backend_properties.repository.PropertyRepository;
import com.graduation.backend_properties.repository.PropertyownerRepository;
import com.graduation.backend_properties.repository.UnauthorizedAccessException;
import com.graduation.backend_properties.repository.UserRepository;
import com.graduation.backend_properties.utils.UserUtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api-version}/owner")
public class Propertyownercontroller {
    private final PropertyownerRepository propertyownerRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;


    public Propertyownercontroller(PropertyownerRepository propertyownerRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyownerRepository = propertyownerRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
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
    public ResponseEntity<Property> addPropertyToOwner(@RequestBody Property property) {
//
        User user = UserUtilsService.getAuthenticatedUser(userRepository);
        if (user != null) {
            property.setOwner(user.getOwner());
        } else {
            property.setOwner(null);
        }
        propertyRepository.save(property);
        return ResponseEntity.ok(property);
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
