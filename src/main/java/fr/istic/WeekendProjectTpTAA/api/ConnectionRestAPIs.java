package fr.istic.WeekendProjectTpTAA.api;


import fr.istic.WeekendProjectTpTAA.repository.UserPplRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(value = "Connection Management System", description = "Operations pertaining to users connected to the application")
public class ConnectionRestAPIs{

    @Autowired
    UserPplRepository userPplRepository;

    @GetMapping("/api/weekend/hello")
    public String helloWord(){

        return "hello word";
    }

    @GetMapping("/api/info/weekend/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess(){
        return ">>> User Contents!";
    }

    @GetMapping("/api/info/weekend/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return ">>> Admin Contents!";
    }

    @GetMapping(value = "/api/info/weekend/registerUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> findAllUsers(){
        return ResponseEntity.ok(userPplRepository.findAll());
    }

}
