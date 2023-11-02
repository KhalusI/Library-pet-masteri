package library.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import library.library.enums.Role;
import library.library.enums.Status;
import library.library.services.BookFileManager;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "user_email")
    @NotNull
    @Email(message = "Invalid email format")
    @Pattern(regexp = ".*@sclnau\\.com\\.ua$", message = "Дозволяються лише електронні адреси sclnau.com.ua")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "is_active")
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "user")
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
        book.setUser(this);
    }

    public void removeBook(Book book){
        BookFileManager fileManager = new BookFileManager();

        book.setUser(null);
        books.remove(book);
        fileManager.deleteBook(book);
    }

    public void removeBook(Long id){
        if(books.stream().anyMatch(b -> b.getId().equals(id))) {
            books.stream().filter(b -> b.getId().equals(id)).findFirst().get().setUser(null);
        }
        books.removeIf(b -> b.getId().equals(id));
    }
}
