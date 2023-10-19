package library.library.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Table(name = "groups")
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_img")
    private String image;

    @OneToMany(mappedBy = "group")
    private List<Subject> subjects = new ArrayList<>();

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public void removeSubjectById(Long id){
        this.subjects.removeIf(subject -> Objects.equals(subject.getId(), id));
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subject);
    }
}
