package org.example.p3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeDTO {


    private Integer user_id ;


    @NotEmpty(message = "cant be empty")
    private String position;


    @NotNull(message = "cant be not null")
    @Positive(message = "positive number only")
    private Integer salary;

}
