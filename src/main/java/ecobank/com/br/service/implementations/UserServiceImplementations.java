package ecobank.com.br.service.implementations;

import ecobank.com.br.model.Account;
import ecobank.com.br.model.Card;
import ecobank.com.br.model.User;
import ecobank.com.br.repository.UserRepository;
import ecobank.com.br.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Transactional
public class UserServiceImplementations implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementations(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User create(User userToCreate) {
        Account account = new Account();
        account.setNumber("35169876" + System.currentTimeMillis());
        account.setBalance(BigDecimal.ZERO);
        userToCreate.setAccount(account);

        return userRepository.save(userToCreate);
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        User user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Account account = user.getAccount();
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
        userRepository.save(user);
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        User user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Account account = user.getAccount();
        if (account.getBalance().compareTo(BigDecimal.valueOf(amount)) >= 0) {
            account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
            userRepository.save(user);
        } else {
            throw new RuntimeException("Insufficient balance");
        }
    }

    @Override
    public void transfer(String fromAccount, String toAccount, double amount) {
        User fromUser = userRepository.findByAccountNumber(fromAccount)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        User toUser = userRepository.findByAccountNumber(toAccount)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        Account fromAcc = fromUser.getAccount();
        Account toAcc = toUser.getAccount();

        if (fromAcc.getBalance().compareTo(BigDecimal.valueOf(amount)) >= 0) {
            fromAcc.setBalance(fromAcc.getBalance().subtract(BigDecimal.valueOf(amount)));
            toAcc.setBalance(toAcc.getBalance().add(BigDecimal.valueOf(amount)));
            userRepository.save(fromUser);
            userRepository.save(toUser);
        } else {
            throw new RuntimeException("Insufficient balance");
        }
    }

    @Override
    public double getBalance(String accountNumber) {
        User user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return user.getAccount().getBalance().doubleValue();
    }

    @Override
    public void createCard(String accountNumber) {
        User user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Card card = new Card();
        card.setNumber("A012555212712354" + System.currentTimeMillis());
        card.setCardLimit(BigDecimal.ZERO);
        user.setCard(card);
        userRepository.save(user);
    }
}
