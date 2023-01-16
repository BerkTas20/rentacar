package com.berktas.rentacar.repository.address;

import com.berktas.rentacar.model.entity.adresses.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

    List<District> findByProvinceName(String provinceName);

    List<District> findByProvinceId(Long provinceId);

    List<District> findByDistrictNameContains(String districtName);

    Optional<District> findByProvinceNameIgnoreCaseAndDistrictNameIgnoreCase(String provinceName, String districtName);

    Optional<District> findByDistrictName(String districtName);

}
