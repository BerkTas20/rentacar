package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.model.entity.CarImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface CarImageService {
    public CarImage getFile(String id);

    public Stream<CarImage> getAllFiles();

    void store(MultipartFile file) throws IOException;
}
