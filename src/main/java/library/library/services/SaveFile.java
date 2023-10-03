package library.library.services;

import library.library.entities.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SaveFile {
    public String saveFileAndGetFilePath(MultipartFile multipartFile, String fileName) throws Exception {
        String filepath = "C:/Users/chalu/Desktop/Library-pet-master/src/main/resources/booksFiles/"+fileName;

        multipartFile.transferTo(new File(filepath));

        return filepath;
    }
}
