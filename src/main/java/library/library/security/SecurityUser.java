package library.library.security;

import library.library.entities.User;
import library.library.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String email;

    private final String password;

    private final List<GrantedAuthority> authorities;

    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        boolean isActive = user.getStatus().equals(Status.ACTIVE);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                isActive,
                isActive,
                isActive,
                isActive,
                user.getRole().getAuthorities()
        );
    }
}
