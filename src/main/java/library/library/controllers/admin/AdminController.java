package library.library.controllers.admin;

import library.library.entities.Book;
import library.library.entities.Group;
import library.library.entities.Subject;
import library.library.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final SaveFile saveFile;
    private final BookService bookService;
    private final FileNameCreator fileNameCreator;

    @Autowired
    public AdminController(GroupService groupService, SubjectService subjectService, SaveFile saveFile, BookService bookService, FileNameCreator fileNameCreator) {
        this.groupService = groupService;
        this.subjectService = subjectService;
        this.saveFile = saveFile;
        this.bookService = bookService;
        this.fileNameCreator = fileNameCreator;
    }

    @GetMapping("/upload")
    public String uploadPdf(Model model){
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("subjects", subjectService.getAll());


        return "admin/upload_pdf";
    }

    @PostMapping("/uploadPost")
    public String handleFormSubmission(@RequestParam("groupId") String groupId,
                                       @RequestParam("subjectId") String subjectId,
                                       @RequestParam("name") String name,
                                       @RequestParam("author") String author,
                                       @RequestParam("file") MultipartFile file) throws Exception {
        if(file != null) {
            Group group = groupService.getById(Long.valueOf(groupId));
            Subject subject = subjectService.getById(Long.valueOf(subjectId));

            String fileName =
                    fileNameCreator.createNameForFile(file, Long.valueOf(groupId), Long.valueOf(subjectId), name, author);
            String filePath =
                    saveFile.saveFileAndGetFilePath(file, fileName);

            Book book = new Book();

            book.setSubject(subject);

            group.set

            book.setName(name);
            book.setAuthor(author);
            book.setPdfPath(filePath);

            bookService.save(book);
        }


        return "redirect:upload";
    }
}
