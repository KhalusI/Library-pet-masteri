package library.library.controllers.index;

import library.library.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
public class SubjectsController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectsController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String subjectIndex(){
        return "subjects/index";
    }

    @GetMapping("/{id}")
    public String subjectPage(@PathVariable("id") String id, Model model){

        model.addAttribute("subject", subjectService.getById(Long.valueOf(id)));

        return "subjects/subject";
    }
}
