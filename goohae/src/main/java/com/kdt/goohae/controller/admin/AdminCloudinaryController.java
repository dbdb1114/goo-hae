package com.kdt.goohae.controller.admin;

import com.kdt.goohae.config.cloudinary.DetailPageCloudinary;
import com.kdt.goohae.service.admin.AdminCloudinaryService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class AdminCloudinaryController {

    private AdminCloudinaryService cloudinaryService;

    @PostMapping("/admin/upload/detail-images")
    @ResponseBody
    public Map<String,String> uploadDetailImages(
            @RequestParam("datailPageImages")ArrayList<MultipartFile> multipartFileArrayList){

        Map<String, String> imgUrlList = cloudinaryService.uploadDetailImages(multipartFileArrayList);

        return imgUrlList;
    }

}
