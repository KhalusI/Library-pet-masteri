package library.library.repositories;

import library.library.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    List<Subject> findAllByGroup_IdIs(Long id);
    Subject findSubjectByGroup_IdIsAndNameIs(Long id, String name);
}
