package ecobank.com.br.controller;


import ecobank.com.br.model.User;
import ecobank.com.br.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        User userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        userService.deposit(accountNumber, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        userService.withdraw(accountNumber, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount) {
        userService.transfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accountNumber) {
        double balance = userService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/createCard")
    public ResponseEntity<Void> createCard(@RequestParam String accountNumber) {
        userService.createCard(accountNumber);
        return ResponseEntity.ok().build();
    }

}
