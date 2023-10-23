package library.library.services.security;

import library.library.entities.User;
import library.library.repositories.UserRepository;
import library.library.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        if(userRepository.findByEmail(username).isPresent()){
            User user = userRepository.findByEmail(username).get();
            return SecurityUser.fromUser(user);
        } else throw new UsernameNotFoundException("");
    }
}
