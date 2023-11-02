package library.library.entities;

import jakarta.persistence.*;
import library.library.services.BookFileManager;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.context.annotation.Lazy;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_author")
    private String author;

    @Column(name = "pdf_path")
    private String pdfPath;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private User user;

    public void removeUser(User user){
        this.user = null;
    }

    public void removeUser(){
        user.removeBook(this);
        this.user = null;
        BookFileManager fileManager = new BookFileManager();
        fileManager.deleteBook(this);
    }
}
