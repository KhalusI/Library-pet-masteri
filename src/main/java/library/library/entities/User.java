package library.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import library.library.enums.Role;
import library.library.enums.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
}
