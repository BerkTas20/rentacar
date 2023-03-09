package com.berktas.rentacar.repository.address;


import com.berktas.rentacar.model.entity.adresses.Streets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StreetRepository extends JpaRepository<Streets, Long> {

    List<Streets> findByneighbourhoodId(Long neighbourhoodId);

    List<Streets> findBystreetNameContains(String streetName);

    Optional<Streets> findBystreetName(String streetName);

}
