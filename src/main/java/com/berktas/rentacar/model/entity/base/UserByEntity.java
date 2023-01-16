package com.berktas.rentacar.model.entity.base;

import lombok.Getter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class UserByEntity extends BaseEntity {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String createdByUsername;
    private String updatedByUsername;

    @PrePersist
    protected void onCreate() {
        updatedDateTime = createdDateTime = LocalDateTime.now(ZoneOffset.UTC);
//        createdByUsername = SpringContext.getCurrentUser().getUsername();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now(ZoneOffset.UTC);
//        updatedByUsername = SpringContext.getCurrentUser().getUsername();
    }

}
