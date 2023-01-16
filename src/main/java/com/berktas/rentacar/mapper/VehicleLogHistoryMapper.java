package com.berktas.rentacar.mapper;

import com.berktas.rentacar.mapper.base.BaseMapper;
import com.berktas.rentacar.model.dto.VehicleLogHistoryDto;
import com.berktas.rentacar.model.entity.CarLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OnlyVehicleInformationMapper.class)
public interface VehicleLogHistoryMapper extends BaseMapper<VehicleLogHistoryDto, CarLog> {
}
