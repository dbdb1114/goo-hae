package com.kdt.goohae.service.admin;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface AdminCloudinaryService {
    Map<String, String> uploadDetailImages(ArrayList<MultipartFile> multipartFileArrayList);
}
