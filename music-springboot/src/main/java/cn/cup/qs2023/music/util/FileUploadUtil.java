package cn.cup.qs2023.music.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

public class FileUploadUtil {
    private static final String UPLOAD_ROOT_DIR = "/var/www/musiconline/uploads/";
    private static final String SUB_DIR_VINYL = "vinyl/";

    public static String saveVinylCover(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFilename = UUID.randomUUID().toString() + extension;
        Path uploadPath = Paths.get(UPLOAD_ROOT_DIR, SUB_DIR_VINYL);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/" + SUB_DIR_VINYL + newFilename;
    }
}
