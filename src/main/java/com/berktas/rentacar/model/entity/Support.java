package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Support extends AbstractTimestampEntity {
    private String issue;

    @ManyToOne
    private Car car;
}
