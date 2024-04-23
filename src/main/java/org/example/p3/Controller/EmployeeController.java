package org.example.p3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.p3.Api.ApiResponse;
import org.example.p3.DTO.EmployeeDTO;
import org.example.p3.Model.User;
import org.example.p3.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user , @RequestBody @Valid EmployeeDTO employeeDTO) {

        employeeService.registerEmployee(user , employeeDTO);

        return ResponseEntity.status(200).body(new ApiResponse("added"));

    }


    @PutMapping("/active")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user , Integer accountId ){
        employeeService.activeAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("activated"));
    }



    @GetMapping("/list-users")
    public ResponseEntity listUsers(){
        return ResponseEntity.status(200).body(employeeService.userList());

    }


    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user ,@PathVariable Integer accountId ){
        employeeService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("account blocked"));
    }

}
