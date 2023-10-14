package ru.kpfu.itis.gimaletdinova.util;

import com.cloudinary.Cloudinary;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadUtil {

    private static final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    private static final String FILE_PATH_PREFIX = "/tmp";
    private static final Integer DIRECTORIES_COUNT = 100;
    public static String saveImg(Part part) throws IOException {

        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File(FILE_PATH_PREFIX +
                File.separator +
                fileName.hashCode() % DIRECTORIES_COUNT +
                File.separator +
                fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();

        try (InputStream inputStream = part.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            int b;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
        }

        if (file.length() == 0) {
            file.delete();
            return null;
        }
//        TODO verify file
        Map uploadResult = cloudinary.uploader().upload(file, new HashMap<>());
        String url = uploadResult.get("url").toString();

        file.delete();

        return url;
    }
}
