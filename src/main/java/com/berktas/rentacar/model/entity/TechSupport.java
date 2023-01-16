package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.controller.techsupport.SaveTechSupportRequest;
import com.berktas.rentacar.model.entity.user.Customer;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("TECH")
public class TechSupport extends Support{
    private String caller;

    public static TechSupport create(SaveTechSupportRequest saveTechSupportRequest, Customer customer, Car car){
        TechSupport techSupport= new TechSupport();
        techSupport.setIssue(saveTechSupportRequest.getIssue());
        techSupport.setCaller(saveTechSupportRequest.getCaller());
        techSupport.setCar(car);
        return techSupport;
    }
}
