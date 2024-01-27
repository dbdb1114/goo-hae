package com.kdt.goohae.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryManager{
    private static final String CLOUD_NAME = "asdfqwe";
    private static final String API_KEY = "164373756496888";
    private static final String API_SECRET = "sfok-FFa7PSI_FP3C5pdoKpXKgI";

    public static final void setDefaultInformation(Cloudinary cloudinary) {
        cloudinary.config.apiKey = API_KEY;
        cloudinary.config.cloudName = CLOUD_NAME;
        cloudinary.config.apiSecret = API_SECRET;
    }

}
