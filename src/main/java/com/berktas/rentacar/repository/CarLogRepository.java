package com.berktas.rentacar.repository;


import com.berktas.rentacar.model.entity.CarLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarLogRepository extends JpaRepository<CarLog, Long> {
    boolean existsByCar_IdAndMqttLogDateTime(Long CarId, LocalDateTime mqttLogDateTime);

   @Query(value = "select distinct vl from CarLog vl join fetch vl.car c WHERE ((vl.mqttLogDateTime between :startDateTime and :endDateTime) and (c.id in :carIdList))")
    List<CarLog> findByDateRange(@Param(value = "startDateTime") LocalDateTime startDateTime, @Param(value = "endDateTime") LocalDateTime endDateTime, @Param("carIdList") List<Long> carIdList);

    Optional<CarLog> findTopByCar_IdAndMqttLogDateTimeLessThanEqualOrderByMqttLogDateTimeDesc(Long carId, LocalDateTime logDateTime);

    @Query(value = "select distinct cl from CarLog cl join fetch cl.car c WHERE ((cl.mqttLogDateTime between :startDateTime and :endDateTime) and (c.id= :carId))")
    List<CarLog> findByCarIdAndDate(@Param(value = "startDateTime") LocalDateTime startDateTime, @Param(value = "endDateTime") LocalDateTime endDateTime, @Param("carId") Long carId);

    void deleteByCar_IdIn(List<Long> carId);

}
