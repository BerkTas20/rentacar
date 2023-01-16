package com.berktas.rentacar.mapper.base;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;


public interface BaseMapper<Dto, Entity> {

    @Named("entityToDto")
    Dto entityToDto(Entity entity);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<Dto> entityListToDtoList(List<Entity> entityList);
}

