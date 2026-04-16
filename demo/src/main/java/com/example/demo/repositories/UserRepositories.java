package com.example.demo.repositories;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String Mailid); //optional forces you to handle excetions(optional is a convention to remind dev that this is expected to thrpw excetion so handle it) as in
                                                // this case user might not exist thats a known case we dont
                                                // want code to throw nullpointer and if we forget to capture it it leads to errors
}
