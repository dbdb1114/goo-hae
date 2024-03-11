package com.kdt.goohae.service.admin;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface AdminCloudinaryService {
    Map<String, String> uploadDetailImages(ArrayList<MultipartFile> multipartFileArrayList, String categoryName, String productName);
    ArrayList<String> uploadMainImages(ArrayList<MultipartFile> multipartFileArrayList, String categoryName, String productName);
    String uploadOptionImage(MultipartFile multipartFile, String categoryName, String productName);
}
