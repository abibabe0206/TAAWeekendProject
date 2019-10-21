package fr.istic.WeekendProjectTpTAA.service;


import fr.istic.WeekendProjectTpTAA.model.DTO.UserDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Users;
import fr.istic.WeekendProjectTpTAA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        Users users = (Users) userRepository.findByUsername(username);
        if (users == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(),
                new ArrayList<>());
        /*if ("abiolaapp".equals(username)){
            return new User("abiolaapp", "$2a$10$mnobD6mHIWfHMUThvkbX4ecbv2WMPcvKgYl3CN51IPITl/tT3dONG",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }*/
    }

    public Users save(UserDTO userDTO){
        Users newUsers = new Users();
        newUsers.setUsername(userDTO.getUsername());
        newUsers.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        return userRepository.save(newUsers);
    }

}
