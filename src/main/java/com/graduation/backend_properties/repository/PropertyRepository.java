package com.graduation.backend_properties.repository;

import com.graduation.backend_properties.modele.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
