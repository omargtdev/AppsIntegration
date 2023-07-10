package pe.isil.microservicios_2978.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.isil.microservicios_2978.exception.ModelNotFoundException;
import pe.isil.microservicios_2978.model.Login;
import pe.isil.microservicios_2978.model.User;
import pe.isil.microservicios_2978.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(Login login) throws ModelNotFoundException {
        Optional<User> searchedUser = userRepository.findByEmail(login.getEmail());
        if(searchedUser.isEmpty())
            throw new ModelNotFoundException("The user with that email was not found");

        User user = searchedUser.get();
        return passwordEncoder.matches(login.getPassword(), user.getPassword());
    }

    public Optional<User> getUser(String email){
        return userRepository.findByEmail(email);
    }

    public boolean addUser(User user){
        user.setId(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }


}
