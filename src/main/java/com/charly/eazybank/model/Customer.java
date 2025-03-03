package com.charly.eazybank.model;

import com.charly.eazybank.dto.RegisterUserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Varchar(45)
    @Column(length = 45)
    private String email;

    @Column(length = 200)
    private String pwd;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return this.pwd;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * Mapper from the RegisterUserDTO to the Customer entity
     * @return the Customer entity
     */
    public static Customer from(RegisterUserDTO registerUserDTO) {
        return new Customer(null, registerUserDTO.getEmail(), registerUserDTO.getPassword(), registerUserDTO.getRoles());
    }

}
