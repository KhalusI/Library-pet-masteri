package library.library.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
