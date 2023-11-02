package library.library.controllers.user;

import library.library.entities.Book;
import library.library.entities.Group;
import library.library.entities.Subject;
import library.library.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final SaveFile saveFile;
    private final BookService bookService;
    private final FileNameCreator fileNameCreator;

    @Autowired
    public UserController(GroupService groupService, SubjectService subjectService, SaveFile saveFile, BookService bookService, FileNameCreator fileNameCreator) {
        this.groupService = groupService;
        this.subjectService = subjectService;
        this.saveFile = saveFile;
        this.bookService = bookService;
        this.fileNameCreator = fileNameCreator;
    }

    @GetMapping("/upload/{id}")
    public String uploadPdf(Model model, @PathVariable Long id){
        model.addAttribute("group", groupService.getById(id));
        model.addAttribute("subjects", groupService.getById(id).getSubjects());
        model.addAttribute("groups", groupService.getAll());

        return "user/upload_pdf";
    }

    @GetMapping("/upload")
    public String uploadIndex(){
        return "redirect:/groups";
    }

    @PostMapping("/uploadPost")
    public String handleFormSubmission(@RequestParam("groupId") String groupId,
                                       @RequestParam("subjectName") String subjectName,
                                       @RequestParam("name") String name,
                                       @RequestParam("author") String author,
                                       @RequestParam("file") MultipartFile file) throws Exception {

        if(file != null) {
            Group group = groupService.getById(Long.valueOf(groupId));
            Subject subject = subjectService.getByNameAndGroupId(subjectName, group.getId());

            Book book = new Book();

            String fileName =
                    fileNameCreator.createNameForFile(file, book.getId());

            String filePath =
                    saveFile.saveFileAndGetFilePath(file, fileName);

            book.setSubject(subject);
            subject.setGroup(group);

            book.setName(name);
            book.setAuthor(author);
            book.setPdfPath(filePath);

            bookService.save(book);
            groupService.save(group);
            bookService.save(book);
        }


        return "redirect:/user/upload/"+groupId+"?success";
    }
}
