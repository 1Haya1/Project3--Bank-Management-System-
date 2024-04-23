package org.example.p3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Employee {

    @Id
    private Integer id;

    @NotEmpty(message = "cant be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String position;

    @NotNull(message = "cant be null")
    @Positive(message = "positive number only")
    @Column(columnDefinition = "int not null")
    private Integer salary;

   @OneToOne
   @MapsId
   @JsonIgnore
    private User user;

}
