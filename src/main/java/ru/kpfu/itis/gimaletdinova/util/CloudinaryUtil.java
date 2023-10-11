package ru.kpfu.itis.gimaletdinova.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

    private static final String CLOUD_NAME = "dziyohpi3";
    private static final String ARI_KEY = "828561926745467";
    private static final String API_SECRET = "W0CiIiOeumO6OYOHMytkeRmVrhA";

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", CLOUD_NAME);
            configMap.put("api_key", ARI_KEY);
            configMap.put("api_secret", API_SECRET);
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }

}
