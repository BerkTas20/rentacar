package com.berktas.rentacar.business.concretes;


import com.berktas.rentacar.business.abstracts.AuthorizationToViewVehicleInformationService;
import com.berktas.rentacar.business.abstracts.CarLogService;
import com.berktas.rentacar.business.abstracts.CarService;
import com.berktas.rentacar.core.exception.EntityNotFoundException;
import com.berktas.rentacar.core.exception.UnauthorizedCarOperationException;
import com.berktas.rentacar.core.exception.UnauthorizedVehicleOperationException;
import com.berktas.rentacar.core.map.DistanceCalculator;
import com.berktas.rentacar.core.util.SpringContext;
import com.berktas.rentacar.core.validator.EmptySetValidator;
import com.berktas.rentacar.core.validator.FutureDateTimeValidator;
import com.berktas.rentacar.core.validator.StartEndDateTimeValidator;
import com.berktas.rentacar.mapper.VehicleLogHistoryMapper;
import com.berktas.rentacar.model.dto.CarLogHistoryDto;
import com.berktas.rentacar.model.dto.EmptySetValidatorDto;
import com.berktas.rentacar.model.dto.StartEndDateTimeDto;
import com.berktas.rentacar.model.dto.VehicleLogHistoryDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.CarLog;
import com.berktas.rentacar.model.entity.user.User;
import com.berktas.rentacar.model.enums.CarIgnitionStatus;
import com.berktas.rentacar.model.enums.CarStatus;
import com.berktas.rentacar.model.enums.Role;
import com.berktas.rentacar.repository.CarLogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CarLogManager implements CarLogService {

    Logger logger = LoggerFactory.getLogger(CarLogManager.class);

    private final CarLogRepository carLogRepository;
    private final CarService carService;
    private final VehicleLogHistoryMapper vehicleLogHistoryMapper;
    private final StartEndDateTimeValidator startEndDateTimeValidator;
    private final EmptySetValidator emptySetValidator;
//    private final SecurityContextUtil securityContextUtil;
    private final AuthorizationToViewVehicleInformationService authorizationToViewVehicleInformationService;
    private final FutureDateTimeValidator futureDateTimeValidator;





    private Boolean distanceControlBetweenTwoLogs(CarLogHistoryDto cache, CarLogHistoryDto currentLog) {
        long distance = currentLog.getCurrentTotalDistance() - cache.getCurrentTotalDistance();
        return distance > 100;
    }

    private Boolean timeControlBetweenTwoLogs(CarLogHistoryDto cache, CarLogHistoryDto currentLog) {
        long minutes = ChronoUnit.MINUTES.between(cache.getMqttLogDateTime(), currentLog.getMqttLogDateTime());
        return minutes > 1;
    }

    private Boolean idleControlBetweenTwoLogs(CarLogHistoryDto previousLog, CarLogHistoryDto currentLog) {
        return !((previousLog.status() == CarIgnitionStatus.STARTED) && (currentLog.status() == CarIgnitionStatus.STARTED));
    }


    @Override
    public Map<Long, List<CarLogHistoryDto>> findByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> carIdList) {
        startEndDateTimeValidator.validate(StartEndDateTimeDto.create(startDateTime, endDateTime));
        futureDateTimeValidator.validate(startDateTime);
        futureDateTimeValidator.validate(endDateTime);

        Set<Car> vehicleSetList = carService.findActiveVehicleListByIdList(carIdList);

        emptySetValidator.validate(EmptySetValidatorDto.create(vehicleSetList, "Araç Bulunamamıştır."));

        User currentUser = SpringContext.getCurrentUser();

        if (currentUser.getRole() == Role.ROLE_ADMIN) {

            return findByDateRangeToMap(startDateTime, endDateTime, carIdList);

        }  else {
            throw new UnauthorizedCarOperationException(HttpStatus.FORBIDDEN, "Yetkisiz İşlem Yapmaktasınız.");
        }
    }

    @Override
    public Map<Long, CarLogHistoryDto> findTimeLineByDateAndVehicleIdList(LocalDateTime logDateTime, List<Long> carIdList) {
        User currentUser = SpringContext.getCurrentUser();
        futureDateTimeValidator.validate(logDateTime);

        Set<Car> vehicleSetList = carService.findActiveVehicleListByIdList(carIdList);
        emptySetValidator.validate(EmptySetValidatorDto.create(vehicleSetList, "Araç Bulunamamıştır."));

        if (currentUser.getRole() == Role.ROLE_ADMIN) {

            return findTimeLineByDateAndVehicleIdListToMap(logDateTime, carIdList);

        }  else {
            throw new UnauthorizedCarOperationException(HttpStatus.FORBIDDEN, "Yetkisiz İşlem Yapmaktasınız.");
        }
    }

    @Override
    public VehicleLogHistoryDto findTimeLineByDateAndCarId(LocalDateTime logDateTime, Long carId) {
        User currentUser = SpringContext.getCurrentUser();
        futureDateTimeValidator.validate(logDateTime);

        Car vehicle = carService.findById(carId);

        if (vehicle.getCarStatus() == CarStatus.PASSIVE) {
            throw new EntityNotFoundException("Araç pasif olduğu için gösterilemiyor.");
        }

        if (currentUser.getRole() == Role.ROLE_ADMIN) {

            Optional<CarLog> carLogOptional = carLogRepository.findTopByCar_IdAndMqttLogDateTimeLessThanEqualOrderByMqttLogDateTimeDesc(carId, logDateTime);

            if (carLogOptional.isPresent()) {
                return vehicleLogHistoryMapper.entityToDto(carLogOptional.get());
            }

        } else {
            throw new UnauthorizedVehicleOperationException(HttpStatus.FORBIDDEN, "Yetkisiz İşlem Yapmaktasınız.");
        }
        //TODO şimdilik böyle kalsın.
        return null;
    }

    @Override
    public List<CarLogHistoryDto> filterCarLogHistoryForIgnitionFalseStatus(List<CarLogHistoryDto> carLogHistoryDtoList) {

        ArrayList<CarLogHistoryDto> filteredList = new ArrayList<>();

        if (carLogHistoryDtoList.size() == 0) {
            return filteredList;
        }

        CarLogHistoryDto cache = carLogHistoryDtoList.get(0);
        filteredList.add(cache);

        for (CarLogHistoryDto dto : carLogHistoryDtoList) {

            boolean isValid = false;
            isValid |= !(cache.getIgnition() == Boolean.FALSE && dto.getIgnition() == Boolean.FALSE);
            isValid |= DistanceCalculator.calculateDistance(cache.getLatLng(),dto.getLatLng()) > 300;

            if (isValid) {
                filteredList.add(dto);
            }
            cache = dto;
        }
        return filteredList;
    }

    @Override
    public List<CarLogHistoryDto> filterCarLogHistoryForIdle(List<CarLogHistoryDto> carLogHistoryDtoList) {
        ArrayList<CarLogHistoryDto> filteredList = new ArrayList<>();

        if (carLogHistoryDtoList.size() == 0) {
            return filteredList;
        }

        //VehicleLogHistoryDto previousLog = vehicleLogHistoryDtoList.get(0);
        CarLogHistoryDto cache = carLogHistoryDtoList.get(0);

        filteredList.add(cache);

        for (CarLogHistoryDto currentLog : carLogHistoryDtoList) {
            if (idleControlBetweenTwoLogs(cache, currentLog) || timeControlBetweenTwoLogs(cache, currentLog) || distanceControlBetweenTwoLogs(cache, currentLog)) {
                filteredList.add(currentLog);
                cache = currentLog;
            }
        }
        return filteredList;    }

    @Override
    public List<CarLogHistoryDto> filterCarLogHistoryFacade(List<CarLogHistoryDto> vehicleLogHistoryDtoList) {
        return null;
    }

    @Override
    public Map<Long, List<CarLogHistoryDto>> findByDateRangeToMap(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> carIdList) {
        return null;
    }

    @Override
    public Map<Long, CarLogHistoryDto> findTimeLineByDateAndVehicleIdListToMap(LocalDateTime logDateTime, List<Long> carIdList) {
        return null;
    }

    @Override
    public List<CarLogHistoryDto> historyReport(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> carIdList) {
        return null;
    }

    @Override
    public List<CarLogHistoryDto> historyOfCar(LocalDateTime startDateTime, LocalDateTime endDateTime, Long carId) {
        return null;
    }
}
