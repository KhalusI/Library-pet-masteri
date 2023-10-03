package library.library.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "group")
    private List<Subject> subjects = new ArrayList<>();

    public void addSubject
}
