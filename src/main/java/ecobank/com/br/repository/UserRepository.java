package ecobank.com.br.repository;

import ecobank.com.br.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    boolean existsByAccountNumber (String accountNumber);
    Optional<User> findByAccountNumber(String accountNumber);

}
