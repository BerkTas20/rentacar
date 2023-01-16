package com.berktas.rentacar.repository.address;

import com.berktas.rentacar.model.entity.adresses.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

    List<Neighborhood> findByProvinceNameAndDistrictName(String provinceName, String districtName);

    List<Neighborhood> findByDistrictId(Long districtId);

    List<Neighborhood> findByNeighborhoodNameContains(String neighborhoodName);

    Optional<Neighborhood> findByNeighborhoodName(String neighborhoodName);

    Optional<Neighborhood> findFirstByProvinceNameIgnoreCaseAndDistrictNameIgnoreCaseAndNeighborhoodNameContainsIgnoreCase(String provinceName, String districtName,String neighborhoodName);

}
