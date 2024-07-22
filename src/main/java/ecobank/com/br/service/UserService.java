package ecobank.com.br.service;

import ecobank.com.br.model.User;

public interface UserService {

    User findById(Long id);
    User create(User userToCreate);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
    void transfer(String fromAccount, String toAccount, double amount);
    double getBalance(String accountNumber);
    void createCard(String accountNumber);

}
