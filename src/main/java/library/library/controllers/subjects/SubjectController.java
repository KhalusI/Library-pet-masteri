package library.library.controllers.subjects;

import library.library.entities.Subject;
import library.library.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/api/subjects")
    public List<Subject> getSubjects(

            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<Subject> allSubjects = subjectService.getAll();

        int endIndex = Math.min(offset + limit, allSubjects.size());
        return allSubjects.subList(offset, endIndex);
    }
}
