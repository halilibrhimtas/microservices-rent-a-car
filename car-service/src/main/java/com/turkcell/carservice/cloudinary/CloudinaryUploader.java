package com.turkcell.carservice.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryUploader {

    private Cloudinary cloudinary;

    public CloudinaryUploader() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dczpvw5al",
                "api_key", "388933195176623",
                "api_secret", "Q9VHAQxix7ddahwJxpdNN6Ydaa8"));
    }

    public String uploadBase64Image(String base64Data) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(base64Data, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}
