package com.charly.eazybank.controller;

import com.charly.eazybank.dto.RegisterUserDTO;
import com.charly.eazybank.model.Customer;
import com.charly.eazybank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
// Mapping all the requests to /api/users to this controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param registerUserDTO the customer to be registered
     * @return response entity with the result of the registration
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            String hashPwd = this.passwordEncoder.encode(registerUserDTO.getPassword());
            registerUserDTO.setPassword(hashPwd);

            // Mapped the DTO to the entity
            Customer newCustomer = this.customerRepository.save(Customer.from(registerUserDTO));

            response.put("OK", newCustomer.getId() > 0);
            if (newCustomer.getId() > 0) {
                response.put("message", "User registered successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.put("message", "An error occurred while registering the user");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("OK", false);
            response.put("message", "An error occurred while registering the user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

}
