package ru.kpfu.itis.gimaletdinova;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.gimaletdinova.util.CloudinaryUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Cloudinary cloudinary = CloudinaryUtil.getInstance();
        File file = new File("C:\\Users\\2aysy\\Downloads\\agro-c4d7b3f00e.png");
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, new HashMap<>());
        for (String s: uploadResult.keySet()) {
            System.out.print(s);
            System.out.print(": ");
            System.out.println(uploadResult.get(s));
        }

    }
}
