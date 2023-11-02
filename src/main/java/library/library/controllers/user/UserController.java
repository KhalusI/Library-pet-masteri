package library.library.controllers.user;

import library.library.entities.Book;
import library.library.entities.Group;
import library.library.entities.Subject;
import library.library.entities.User;
import library.library.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {
    private final GroupService groupService;
    private final UserService userService;
    private final SubjectService subjectService;
    private final BookFileManager bookFileManager;
    private final BookService bookService;
    private final FileNameCreator fileNameCreator;

    @Autowired
    public UserController(GroupService groupService, UserService userService, SubjectService subjectService, BookFileManager bookFileManager, BookService bookService, FileNameCreator fileNameCreator) {
        this.groupService = groupService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.bookFileManager = bookFileManager;
        this.bookService = bookService;
        this.fileNameCreator = fileNameCreator;
    }

    @GetMapping("/profile")
    public String userProfile(Model model){
        if (userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
            User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            model.addAttribute("user", user);
            model.addAttribute("groups", groupService.getAll());
        }
        return "user/profile";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") Long bookId){
        Book book = bookService.getById(bookId);

        if (userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
            User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            user.removeBook(bookId);
        }

        bookFileManager.deleteBook(book);

        bookService.remove(bookId);

        return "redirect:/user/profile";
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
            bookService.save(book);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            subject.setGroup(group);

            String fileName =
                    fileNameCreator.createNameForFile(file, book.getId());

            String filePath =
                    bookFileManager.saveFileAndGetFilePath(file, fileName);

            book.setName(name);
            book.setAuthor(author);
            book.setPdfPath(filePath);
            book.setSubject(subject);

            if(userService.getByEmail(authentication.getName()).isPresent()) {
                User user = userService.getByEmail(authentication.getName()).get();
                user.addBook(book);
                userService.save(user);
            }

            bookService.save(book);
            groupService.save(group);
            bookService.save(book);
        }


        return "redirect:/user/upload/"+groupId+"?success";
    }
}
