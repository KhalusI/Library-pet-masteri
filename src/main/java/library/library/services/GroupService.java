package library.library.services;

import library.library.entities.Group;
import library.library.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return groupRepo.findAll(sort);
    }
//    public List<Group> getAll() {
//        int page = 0;
//        List<Group> allGroups = new ArrayList<>();
//
//        while (true) {
//            Page<Group> groupPage = groupRepo.findAll(PageRequest.of(page, 10));
//
//            if (groupPage.hasContent()) {
//                allGroups.addAll(groupPage.getContent());
//            } else {
//                break;
//            }
//            page++;
//        }
//
//        return allGroups;
//    }
}
