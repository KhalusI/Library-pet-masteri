package library.library.controllers.test;

import library.library.entities.Subject;
import library.library.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final SubjectService subjectService;

    @Autowired
    public TestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/test")
    public List<Subject> test(){
        return subjectService.getAll();
    }
}
