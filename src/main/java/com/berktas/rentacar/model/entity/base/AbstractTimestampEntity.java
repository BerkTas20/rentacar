package com.berktas.rentacar.model.entity.base;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Getter
@ToString
public abstract class AbstractTimestampEntity extends BaseEntity {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @PrePersist
    protected void onCreate() {
        updatedDateTime = createdDateTime = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now(ZoneOffset.UTC);
    }
}
