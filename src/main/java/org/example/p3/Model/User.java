package org.example.p3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "cant be empty")
    @Size(min = 4 , max = 10 , message = "username length must be between 4 and 10 characters")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;


    @NotEmpty(message = "password should be not empty")
    @Size(min = 6 , message = "password length must be at least 6 characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String password;



    @NotEmpty(message = "name should be not empty")
    @Size(min = 2 , max = 20 , message = "name length must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null ")
    private String name;


    @Email(message = "please enter a valid email")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email ;


    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$" , message = "Must be either CUSTOMER ,EMPLOYEE only.")
    @Column(columnDefinition = "varchar(8)")
    private String role;





    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
