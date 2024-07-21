package ir.maktab.home_services.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtil {
    public static byte[] readFileToByteArray(String filePath) {
        try {
            File imageFile = new File(filePath);
            FileInputStream fis = new FileInputStream(imageFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            byte[] imageData = bos.toByteArray();

            bos.close();
            fis.close();

            return imageData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
