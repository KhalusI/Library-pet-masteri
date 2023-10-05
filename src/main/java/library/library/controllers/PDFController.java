package library.library.controllers;

import library.library.entities.Book;
import library.library.services.BookService;
import library.library.services.PathToFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;

@Controller
@RequestMapping("/pdf")
public class PDFController {

    private final BookService bookService;
    private final PathToFileName pathToFileName;


    @Autowired
    public PDFController(BookService bookService, PathToFileName pathToFileName) {
        this.bookService = bookService;
        this.pathToFileName = pathToFileName;
    }

    @GetMapping(value = "/previewPDF/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> previewPDF(@PathVariable(name = "id") Long id) throws Exception {

        Book book = bookService.getById(id);

        String pdfFileName = pathToFileName.getFileNameFromPath(book.getPdfPath());

        Path path = Paths.get("src/main/resources/booksFiles/"+pdfFileName);
        byte[] pdf = Files.readAllBytes(path);
//        pdfFileName = pdfFileName.replaceAll("[^\\x00-\\x7F]", "");
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDisposition(ContentDisposition.builder("inline").filename(pdfFileName).build());
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
