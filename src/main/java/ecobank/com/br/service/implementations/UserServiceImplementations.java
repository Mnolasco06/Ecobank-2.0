package ecobank.com.br.service.implementations;

import ecobank.com.br.model.User;
import ecobank.com.br.repository.UserRepository;
import ecobank.com.br.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImplementations implements UserService {


    private final UserRepository userRepository;

    public UserServiceImplementations(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException :: new);
    }

    @Override
    public User create(User userToCreate) {
        if (userToCreate.getId() != null && userRepository.existsById(userToCreate.getId())) {
            throw new IllegalArgumentException("This user id already exist.");
        }

        return userRepository.save(userToCreate);
    }
}
