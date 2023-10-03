package library.library.services;

import library.library.entities.Group;
import library.library.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepo groupRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public void save(Group group){
        groupRepo.save(group);
    }

    public Group getById(Long id){
        return groupRepo.getReferenceById(id);
    }

    public List<Group> getAll(){
        return groupRepo.findAll();
    }
}
