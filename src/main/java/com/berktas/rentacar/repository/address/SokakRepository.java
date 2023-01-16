package com.berktas.rentacar.repository.address;


import com.berktas.rentacar.model.entity.adresses.Sokaklar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SokakRepository extends JpaRepository<Sokaklar, Long> {

    List<Sokaklar> findByMahalleId(Long mahalleId);

    List<Sokaklar> findBySokakAdiContains(String sokakAdi);

    Optional<Sokaklar> findBySokakAdi(String sokakAdi);

}
