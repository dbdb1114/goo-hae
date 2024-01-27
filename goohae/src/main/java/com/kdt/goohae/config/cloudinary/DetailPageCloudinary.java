package com.kdt.goohae.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DetailPageCloudinary extends Cloudinary{
    private static final String PUBLIC_ID_GOOHAE_DTAIL_PAGE = "/goo-hae/detail-page";
    public DetailPageCloudinary() {
        CloudinaryManager.setDefaultInformation(this);
    }

    public <T> Map upload(T file, String productName) throws IOException {
        return this.uploader().upload(file, ObjectUtils.asMap("folder", PUBLIC_ID_GOOHAE_DTAIL_PAGE + "/" + productName));
    }

}
