package com.kdt.goohae.controller.admin;

import com.kdt.goohae.service.admin.AdminCloudinaryService;
import java.util.ArrayList;
import java.util.Map;
import lombok.RequiredArgsConstructor;
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

    private final AdminCloudinaryService cloudinaryService;

    @PostMapping("/admin/upload/detail-images")
    public Map<String,String> uploadDetailImages(
            @RequestParam("detailPageImages") ArrayList<MultipartFile> detailPageImages, @RequestParam("categoryName") String categoryName){
        Map<String, String> imgUrlList = cloudinaryService.uploadDetailImages(detailPageImages, categoryName);
        return imgUrlList;
    }

}
