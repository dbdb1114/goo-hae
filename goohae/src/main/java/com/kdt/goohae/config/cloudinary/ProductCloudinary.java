package com.kdt.goohae.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductCloudinary extends Cloudinary{
    private static final String PUBLIC_ID_GOOHAE = "/goo-hae/product/";

    public ProductCloudinary() {
        CloudinaryManager.setDefaultInformation(this);
    }

    public <T> Map upload(T file, String productName) throws IOException {
        return this.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + productName));
    }

    public <T> Map uploadDetail(T file, String category, String productName) throws IOException {
        return this.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + category + "/" + productName + "/detail"));
    }

    public <T> Map uploadMain(T file, String category, String productName) throws IOException {
        return this.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + category + "/" + productName + "/main"));
    }

    public <T> Map uploadOption(T file, String category, String productName) throws IOException {
        return this.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE + "/" + category + "/" + productName + "/option"));
    }

}
