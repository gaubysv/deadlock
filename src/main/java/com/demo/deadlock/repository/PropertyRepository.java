package com.demo.deadlock.repository;

import com.demo.deadlock.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Property getPropertyByKey(String key);
}
