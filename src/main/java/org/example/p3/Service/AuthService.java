package org.example.p3.Service;

import lombok.RequiredArgsConstructor;
import org.example.p3.Api.ApiException;
import org.example.p3.Model.User;
import org.example.p3.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;



    public User getUserById(Integer userId){
        return authRepository.findUserById(userId);
    }


    public List<User> getAllUsers() {
        return authRepository.findAll();
    }


    public void updateUser(Integer userId, User updatedUser) {
        User user = authRepository.findUserById(userId);

        if(user == null)
            throw new ApiException("User not found");

        user.setUsername(updatedUser.getUsername());
        if(updatedUser.getPassword() != null){
            String hashPassword = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
            user.setPassword(hashPassword);
        }

        authRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        User user = authRepository.findUserById(userId);

        if(user == null)
            throw new ApiException("User not found");

        authRepository.delete(user);
    }

}
