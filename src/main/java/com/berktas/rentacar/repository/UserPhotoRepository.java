package com.berktas.rentacar.repository;

import com.berktas.rentacar.model.entity.CarImage;
import com.berktas.rentacar.model.entity.user.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, String> {
}
