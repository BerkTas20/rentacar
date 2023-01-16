package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.model.entity.user.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface UserPhotoService {
    public UserPhoto getFile(String id);

    public Stream<UserPhoto> getAllFiles();

    void store(MultipartFile file) throws IOException;
}
