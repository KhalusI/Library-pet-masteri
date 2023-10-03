package library.library.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "group_id_fk")
    @ToString.Exclude
    private Group group;
}
