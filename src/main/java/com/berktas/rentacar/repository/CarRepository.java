package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.Car;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("FROM Car WHERE id=?1")
    Optional<Car> lockById(Long id);

    Optional<Car> findByImei(String imei);
    Set<Car> findByIdIn(List<Long> idList);
}
