package fr.istic.WeekendProjectTpTAA.api;


import fr.istic.WeekendProjectTpTAA.model.domain.*;
import fr.istic.WeekendProjectTpTAA.model.jwtModel.ResponseJwt;
import fr.istic.WeekendProjectTpTAA.model.jwtModel.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import fr.istic.WeekendProjectTpTAA.config.userConfig.JwtProvider;
import fr.istic.WeekendProjectTpTAA.repository.RoleRepository;
import fr.istic.WeekendProjectTpTAA.repository.UserPplRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/weekend", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Authentication Management System", description = "Operations pertaining to authenticating the application")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserPplRepository userPplRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping(path = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new ResponseJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userPplRepository.existsByUsername(signUpRequest.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userPplRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating User's Account
        UserPpl userPpl = new UserPpl(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role){
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
                    System.out.print("this is for adminRole" + adminRole);
                    if (adminRole == null){
                        throw new RuntimeException("Fail! -> Cause: User Role not find.");
                    }
                    roles.add(adminRole);
                          /*  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                            roles.add(adminRole);*/
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
                    if (userRole == null){
                        throw new RuntimeException("Fail! -> Cause: User Role not find.");
                    }
                    roles.add(userRole);
            }
        });

        userPpl.setRoles(roles);
        userPplRepository.save(userPpl);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

}