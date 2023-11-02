package library.library.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class FileNameCreator {
    public String createNameForFile(MultipartFile file, Long groupId, Long subjectId, String name, String author){
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).substring(0, file.getOriginalFilename().length() - 4) + groupId +
                subjectId +
                name +
                author +
                ".pdf";

        return String.valueOf(fileName);
    }

    public String createNameForFile(MultipartFile file, Long bookId) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (InputStream is = new FileInputStream(String.valueOf(file))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        byte[] digest = md.digest();
        StringBuilder result = new StringBuilder();
        for (byte b : digest) {
            result.append(String.format("%02x", b));
        }

        return result.append(bookId).toString();
    }
}
