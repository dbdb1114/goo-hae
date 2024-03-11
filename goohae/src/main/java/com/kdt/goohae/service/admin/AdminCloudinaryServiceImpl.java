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
    public Map<String, String> uploadDetailImages(ArrayList<MultipartFile> multipartFileArrayList, String category, String productName) {
        Map<String, String> imgUrlList = new HashMap<>();
        multipartFileArrayList.stream().parallel().forEach(multipartFile -> {
            try{

                String key = multipartFile.getOriginalFilename().split("#")[1];

                Map uploadResult = productCloudinary.uploadDetail(multipartFile.getBytes(), category ,productName);
                String secureUrl = (String) uploadResult.get("secure_url");

                imgUrlList.put(key,secureUrl);
            } catch (IOException e) {
                imgUrlList.put(multipartFile.getOriginalFilename().split("#")[1],"error");
                throw new RuntimeException(e);
            }
        });
        return imgUrlList;
    }

    @Override
    public ArrayList<String> uploadMainImages(ArrayList<MultipartFile> multipartFileArrayList, String category, String productName) {
        ArrayList<String> imgUrlList = new ArrayList<>();

        for(int i = 0; i < multipartFileArrayList.size(); i++){
            MultipartFile multipartFile = multipartFileArrayList.get(i);
            try{
                Map uploadResult = productCloudinary.uploadMain(multipartFile.getBytes(), category, productName);
                String secureUrl = (String) uploadResult.get("secure_url");

                imgUrlList.add(secureUrl);
            } catch (IOException e) {
                imgUrlList.add("error");
                throw new RuntimeException(e);
            }
        }

        return imgUrlList;
    }

    @Override
    public String uploadOptionImage(MultipartFile multipartFile, String category, String productName) {
        String url = "";
        try{
            Map uploadResult = productCloudinary.uploadOption(multipartFile.getBytes(), category, productName);
            url = (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            url = "error";
            throw new RuntimeException(e);
        }
        return url;
    }
}
