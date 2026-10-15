package com.eventhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eventhub.entity.Decoration;

public interface DecorationRepository extends JpaRepository<Decoration, Integer> {

}