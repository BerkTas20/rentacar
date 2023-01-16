package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.CarImageService;
import com.berktas.rentacar.model.entity.CarImage;
import com.berktas.rentacar.repository.CarImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class CarImageManager implements CarImageService {
    private final CarImageRepository carImageRepository;

    @Override
    public CarImage getFile(String id) {
        return carImageRepository.findById(id).get();
    }

    @Override
    public Stream<CarImage> getAllFiles() {
        return carImageRepository.findAll().stream();
    }

    @Override
    public void store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        CarImage FileDB = new CarImage(fileName, file.getContentType(), file.getBytes());

        carImageRepository.save(FileDB);
    }
}
