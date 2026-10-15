package com.eventhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.eventhub.entity.User;
import com.eventhub.repository.UserRepository;

@SpringBootApplication
public class EventhubBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(EventhubBackendApplication.class, args);

        System.out.println("EventHub backend started successfully!");
    }

    // Create the manager accounts when Spring Boot starts.
    @Bean
    CommandLineRunner createManagerAccounts(UserRepository userRepository) {

        return args -> {

            createManager(
                    userRepository,
                    "Ananya Rao",
                    "blr.manager@eventhub.com",
                    "9876500001",
                    "Blr@12345",
                    "CITY_MANAGER",
                    "Bengaluru"
            );

            createManager(
                    userRepository,
                    "Priya Shetty",
                    "mysuru.manager@eventhub.com",
                    "9876500002",
                    "Mysuru@12345",
                    "CITY_MANAGER",
                    "Mysuru"
            );

            createManager(
                    userRepository,
                    "Rahul Kumar",
                    "shivamogga.manager@eventhub.com",
                    "9876500003",
                    "Shiv@12345",
                    "CITY_MANAGER",
                    "Shivamogga"
            );

            createManager(
                    userRepository,
                    "Arjun Gowda",
                    "hassan.manager@eventhub.com",
                    "9876500004",
                    "Hassan@12345",
                    "CITY_MANAGER",
                    "Hassan"
            );

            createManager(
                    userRepository,
                    "Vikram Rao",
                    "head.manager@eventhub.com",
                    "9876500005",
                    "Head@12345",
                    "HEAD_MANAGER",
                    "ALL"
            );
        };
    }

    private void createManager(
            UserRepository userRepository,
            String fullName,
            String email,
            String phone,
            String password,
            String role,
            String city) {

        // Prevent duplicate manager accounts after every restart.
        if (userRepository.findByEmail(email).isPresent()) {
            return;
        }

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole(role);
        user.setCity(city);

        userRepository.save(user);

        System.out.println(
                "Manager created: "
                + fullName
                + " | "
                + role
                + " | "
                + city
        );
    }
}