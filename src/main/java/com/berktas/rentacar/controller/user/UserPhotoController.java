package com.berktas.rentacar.controller.user;


import com.berktas.rentacar.business.abstracts.CarImageService;
import com.berktas.rentacar.business.abstracts.UserPhotoService;
import com.berktas.rentacar.core.util.message.ResponseMessage;
import com.berktas.rentacar.core.util.message.ResponsePhotoUpload;
import com.berktas.rentacar.model.entity.CarImage;
import com.berktas.rentacar.model.entity.user.UserPhoto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userImage")
@RequiredArgsConstructor
@Tag(name = "User Image")
public class UserPhotoController {
    private final UserPhotoService userPhotoService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            userPhotoService.store(file);

            message = "Dosya başarıyla yüklendi: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Dosya yüklenemedi: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<ResponsePhotoUpload>> getListFiles() {
        List<ResponsePhotoUpload> files = userPhotoService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponsePhotoUpload(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        UserPhoto photoUpload = userPhotoService.getFile(id);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"" + photoUpload.getName() + "\"")
                .body(photoUpload.getData());
    }
}
