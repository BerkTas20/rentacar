package com.berktas.rentacar.core.util.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponsePhotoUpload {
    private String name;
    private String url;
    private String type;
    private long size;

    public ResponsePhotoUpload(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}
