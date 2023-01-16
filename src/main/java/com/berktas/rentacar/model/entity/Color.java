package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.controller.color.SaveColorRequest;
import com.berktas.rentacar.controller.color.UpdateColorRequest;
import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
public class Color extends AbstractTimestampEntity {

    private String colorName;

    public static Color create(SaveColorRequest saveColorRequest){
        Color color= new Color();
        color.setColorName(saveColorRequest.getColorName());
        return color;
    }

    public Color update(UpdateColorRequest updateColorRequest){
        setColorName(updateColorRequest.getColorName());
        return this;
    }

}
