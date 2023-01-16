package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.UserPhotoService;
import com.berktas.rentacar.model.entity.CarImage;
import com.berktas.rentacar.model.entity.user.UserPhoto;
import com.berktas.rentacar.repository.UserPhotoRepository;
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
public class UserPhotoManager implements UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;

    @Override
    public UserPhoto getFile(String id) {
        return userPhotoRepository.findById(id).get();
    }

    @Override
    public Stream<UserPhoto> getAllFiles() {
        return userPhotoRepository.findAll().stream();
    }

    @Override
    public void store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserPhoto FileDB = new UserPhoto(fileName, file.getContentType(), file.getBytes());

        userPhotoRepository.save(FileDB);
    }
}
