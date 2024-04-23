package org.example.p3.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomerDTO {


    private Integer user_id ;


    @NotEmpty(message = "cant be not empty")
    //@Pattern(regexp = "^05.*",message = "salary must start with 05")
    private String phoneNumber;
}
