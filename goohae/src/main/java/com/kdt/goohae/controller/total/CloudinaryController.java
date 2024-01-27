package com.kdt.goohae.controller.total;

import com.kdt.goohae.config.cloudinary.DetailPageCloudinary;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cloudinary")
public class CloudinaryController {

    @Autowired
    private DetailPageCloudinary detailPageCloudinary;

    @PostMapping("/admin/upload-image")
    @ResponseBody
    public String uploadProductImage( @RequestParam("detailpageImage") MultipartFile multipartFile){
        if(!multipartFile.isEmpty()){
            try{
                Map uploadResult = detailPageCloudinary.upload(multipartFile.getBytes(),"test");
                String secureUrl = (String) uploadResult.get("secure_url");
                return secureUrl;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "error";
    }

}
