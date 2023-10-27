package library.library.controllers;

import library.library.entities.Book;
import library.library.services.BookService;
import library.library.services.PathToFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/pdf")
public class PDFController {

    private final BookService bookService;
    private final PathToFileName pathToFileName;

    @Value("${upload.path}")
    String fullPath;

    @Autowired
    public PDFController(BookService bookService, PathToFileName pathToFileName) {
        this.bookService = bookService;
        this.pathToFileName = pathToFileName;
    }

    @GetMapping(value = "/previewPDF/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> previewPDF(@PathVariable(name = "id") Long id) throws Exception {

        Book book = bookService.getById(id);

        String pdfFileName = pathToFileName.getFileNameFromPath(book.getPdfPath());

        Path path = Paths.get(fullPath+pdfFileName);

        byte[] pdf = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
