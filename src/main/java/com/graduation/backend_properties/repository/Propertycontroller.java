package com.graduation.backend_properties.repository;

import com.graduation.backend_properties.modele.Property;
import com.graduation.backend_properties.modele.PropertyOwner;
import com.graduation.backend_properties.modele.PropertyOwner;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class Propertycontroller {
    private PropertyRepository propertyRepository;

    public Propertycontroller(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public List<Property> findAllProperties(Property property) {

        return propertyRepository.findAll();
    }

    @GetMapping("/id")
    public Optional<Property> FindById(@PathVariable("id") Long id) {

        return propertyRepository.findById(id);
    }

    @PostMapping
    public Property addproperty(@RequestBody Property property) {
//        PropertyOwner owner = property.getOwner();
//        if (owner == null) {
//            throw new IllegalArgumentException("Le propriétaire du bien immobilier doit être spécifié");
//        }
//        property.setOwner(owner);
        return propertyRepository.save(property);
    }

    @DeleteMapping("/properties/{propertyId}")
    public void deleteProperty(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Bien immobilier non trouvé"));
        propertyRepository.delete(property);
    }

    @PutMapping("/properties/{propertyId}")
    public Property updateProperty(@PathVariable Long propertyId, @RequestBody Property updatedProperty) {
        Property existingProperty = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Bien immobilier non trouvé"));
        propertyRepository.save(existingProperty);

        return existingProperty;
    }
}



