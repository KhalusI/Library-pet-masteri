package library.library.services;

import library.library.entities.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SaveFile {
    @Value("${upload.path}")
    String filepath;
    public String saveFileAndGetFilePath(MultipartFile multipartFile, String fileName) throws Exception {


        multipartFile.transferTo(new File(filepath+fileName));

        return filepath+fileName;
    }
}
