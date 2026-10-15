package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.ForgotPasswordDto;
import com.eventhub.dto.LoginDto;
import com.eventhub.dto.LoginResponseDto;
import com.eventhub.dto.UserDto;
import com.eventhub.entity.User;
import com.eventhub.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ==========================================
    // REGISTER CUSTOMER
    // ==========================================

    public String registerUser(UserDto userDto) {

        User user = new User();

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());

        // Public registration is only for customers.
        user.setRole("CUSTOMER");

        // Customers are not assigned a manager city.
        user.setCity(null);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // ==========================================
    // LOGIN
    // ==========================================

    public LoginResponseDto loginUser(LoginDto loginDto) {

        Optional<User> optionalUser =
                userRepository.findByEmail(loginDto.getEmail());

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(loginDto.getPassword())) {
            return null;
        }

        LoginResponseDto response = new LoginResponseDto();

        response.setMessage("Login successful");
        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setCity(user.getCity());

        return response;
    }

    // ==========================================
    // FORGOT PASSWORD
    // ==========================================

    public String resetPassword(ForgotPasswordDto forgotPasswordDto) {

        Optional<User> optionalUser =
                userRepository.findByEmail(forgotPasswordDto.getEmail());

        if (optionalUser.isEmpty()) {
            return "No account found with this email.";
        }

        User user = optionalUser.get();

        user.setPassword(forgotPasswordDto.getNewPassword());

        userRepository.save(user);

        return "Password reset successfully.";
    }

    // ==========================================
    // GET ALL USERS
    // ==========================================

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // ==========================================
    // GET USER BY ID
    // ==========================================

    public User getUserById(Integer id) {

        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // ==========================================
    // UPDATE USER
    // ==========================================

    public String updateUser(Integer id, UserDto userDto) {

        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {

            User user = optional.get();

            user.setFullName(userDto.getFullName());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());
            user.setPassword(userDto.getPassword());

            if (userDto.getRole() != null &&
                !userDto.getRole().trim().isEmpty()) {

                user.setRole(userDto.getRole());
            }

            user.setCity(userDto.getCity());

            userRepository.save(user);

            return "User Updated Successfully";
        }

        return "User Not Found";
    }

    // ==========================================
    // DELETE USER
    // ==========================================

    public String deleteUser(Integer id) {

        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {

            userRepository.deleteById(id);

            return "User Deleted Successfully";
        }

        return "User Not Found";
    }
}