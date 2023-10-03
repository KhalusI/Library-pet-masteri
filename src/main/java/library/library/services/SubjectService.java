package library.library.services;

import library.library.entities.Group;
import library.library.entities.Subject;
import library.library.repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public void save(Subject subject){
        subjectRepo.save(subject);
    }

    public Subject getById(Long id){
        return subjectRepo.getReferenceById(id);
    }

    public List<Subject> getAll(){
        return subjectRepo.findAll();
    }
}
