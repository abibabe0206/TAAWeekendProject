package fr.istic.WeekendProjectTpTAA.service;


import fr.istic.WeekendProjectTpTAA.model.domain.UserPpl;
import fr.istic.WeekendProjectTpTAA.repository.UserPplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

@Service
public class UserPplDetailsService implements UserDetailsService {

    @Autowired
    private UserPplRepository userPplRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserPpl userPpl  = userPplRepository.findByUsername(username);
        if (userPpl == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return UserPrinciple.build(userPpl);
    }

}
