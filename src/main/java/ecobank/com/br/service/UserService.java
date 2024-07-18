package ecobank.com.br.service;

import ecobank.com.br.model.User;

public interface UserService {


    User findById(Long id);


    User create(User userToCreate);

}
