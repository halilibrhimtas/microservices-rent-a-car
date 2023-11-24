package com.turkcell.carservice.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

    public String uploadBase64Image(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
        Map<?, ?> uploadResult = cloudinary.uploader().upload(base64, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }

}
