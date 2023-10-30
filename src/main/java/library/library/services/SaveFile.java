package library.library.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class SaveFile {
    @Value("${upload.path}")
    String filepath;
    public String saveFileAndGetFilePath(MultipartFile multipartFile, String fileName) throws Exception {

        multipartFile.transferTo(new File(filepath+fileName));

        return filepath+fileName;
    }
}
