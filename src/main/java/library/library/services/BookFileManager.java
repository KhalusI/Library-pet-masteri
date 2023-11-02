package library.library.services;

import library.library.entities.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BookFileManager {
    @Value("${upload.path}")
    String filepath;
    public String saveFileAndGetFilePath(MultipartFile multipartFile, String fileName) throws Exception {

        multipartFile.transferTo(new File(filepath+fileName));

        return filepath+fileName;
    }

    public void deleteBook(Book book){
        File file = new File(book.getPdfPath());
        file.delete();
    }
}
