package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.controller.brand.SaveBrandRequest;
import com.berktas.rentacar.controller.brand.UpdateBrandRequest;
import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class Brand extends AbstractTimestampEntity {

    private String brandName;

    public static Brand create(SaveBrandRequest saveBrandRequest){
        Brand brand= new Brand();
        brand.setBrandName(saveBrandRequest.getBrandName());
        return brand;
    }


    public Brand update(UpdateBrandRequest updateBrandRequest){
        setBrandName(updateBrandRequest.getBrandName());
        return this;
    }

}
