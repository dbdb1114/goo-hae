package com.kdt.goohae.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ProductCloudinary {
    private static final String PUBLIC_ID_GOOHAE = "/goo-hae/product/";

    private static Cloudinary cloudinary;

    public ProductCloudinary() {
        cloudinary = new Cloudinary();
        CloudinaryManager.setDefaultInformation(cloudinary);
    }

    public static  <T> Map upload(T file, String productName) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + productName));
    }

    public static <T> Map uploadDetail(T file, String category, String productName) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + category + "/" + productName + "/detail"));
    }

    public static <T> Map uploadMain(T file, String category, String productName) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + category + "/" + productName + "/main"));
    }

    public static <T> Map uploadOption(T file, String category, String productName) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + category + "/" + productName + "/option"));
    }

}
