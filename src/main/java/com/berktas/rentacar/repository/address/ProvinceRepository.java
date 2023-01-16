package com.berktas.rentacar.repository.address;

import com.berktas.rentacar.model.entity.adresses.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {


    List<Province> findByProvinceNameContains(String provinceName);

    Optional<Province> findByProvinceNameIgnoreCase(String provinceName);

}
