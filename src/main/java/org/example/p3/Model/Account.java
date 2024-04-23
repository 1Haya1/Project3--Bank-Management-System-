package org.example.p3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")
    @Column(columnDefinition = "int")
    private Integer accountNumber;

    @NotNull(message = "cant be empty")
    @Positive(message = "cant be positive number")
    @Column(columnDefinition = "int")
    private Integer balance;


    @AssertFalse
    @Column(columnDefinition = "boolean")
    private Boolean isActive;


    @ManyToOne
    @JsonIgnore
    private Customer customer;



}
