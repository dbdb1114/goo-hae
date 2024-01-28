package com.kdt.goohae.service.admin;

import com.kdt.goohae.config.cloudinary.ProductCloudinary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AdminCloudinaryServiceImpl implements AdminCloudinaryService{

    private final ProductCloudinary productCloudinary;
    @Override
    public Map<String, String> uploadDetailImages(ArrayList<MultipartFile> multipartFileArrayList, String categoryName) {
        Map<String, String> imgUrlList = new HashMap<>();

        multipartFileArrayList.stream().forEach(multipartFile -> {
            try{

                String productName = multipartFile.getOriginalFilename().split("#")[0];
                String key = multipartFile.getOriginalFilename().split("#")[1];

                Map uploadResult = productCloudinary.uploadDetail(multipartFile.getBytes(), categoryName ,productName);
                String secureUrl = (String) uploadResult.get("secure_url");

                imgUrlList.put(key,secureUrl);
            } catch (IOException e) {
                imgUrlList.put(multipartFile.getOriginalFilename().split("#")[1],"error");
                throw new RuntimeException(e);
            }
        });

        return imgUrlList;
    }
}
