package library.library.services;


import library.library.entities.User;
import library.library.enums.Status;
import library.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void remove(User user){
        userRepository.delete(user);
    }

    public void remove(Long id){
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User getById(Long id){
        return userRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void removeAll(){
        userRepository.deleteAll();
    }

    public void ban(User user){
        user.setStatus(Status.BANNED);
        save(user);
    }


    public void ban(Long id){
        User user = getById(id);
        user.setStatus(Status.BANNED);
        save(user);
    }


    public void unban(User user){
        user.setStatus(Status.ACTIVE);
        save(user);
    }


    public void unban(Long id){
        User user = getById(id);
        user.setStatus(Status.ACTIVE);
        save(user);
    }
}
