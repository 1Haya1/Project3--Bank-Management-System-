package org.example.p3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.p3.Model.User;
import org.example.p3.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthController {

    private final AuthService authService;



    @GetMapping("/get")
    public ResponseEntity getUserById(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(authService.getUserById(user.getId()));
    }



    @GetMapping("/get-all-user")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @RequestBody @Valid User user) {
        authService.updateUser(userId, user);
        return ResponseEntity.status(200).body(("User updated successfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        authService.deleteUser(userId);
        return ResponseEntity.status(200).body(("User deleted successfully"));
    }



}
