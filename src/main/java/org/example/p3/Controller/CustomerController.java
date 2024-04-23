package org.example.p3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.p3.Api.ApiResponse;
import org.example.p3.DTO.CustomerDTO;
import org.example.p3.Model.User;
import org.example.p3.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user , @RequestBody @Valid CustomerDTO customerDTO) {

        customerService.registerCustomer(user , customerDTO);

        return ResponseEntity.status(200).body(new ApiResponse("customer added"));

    }
}
