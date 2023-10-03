package library.library.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Book> books;

    @ManyToOne
    private Group group;
}
