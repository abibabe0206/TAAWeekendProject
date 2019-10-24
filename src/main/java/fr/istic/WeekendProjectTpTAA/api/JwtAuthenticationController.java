package fr.istic.WeekendProjectTpTAA.api;


import fr.istic.WeekendProjectTpTAA.config.jwtConfig.JwtTokenUtil;
import fr.istic.WeekendProjectTpTAA.model.DTO.UserDTO;
import fr.istic.WeekendProjectTpTAA.model.jwtModel.JwtRequest;
import fr.istic.WeekendProjectTpTAA.model.jwtModel.JwtResponse;
import fr.istic.WeekendProjectTpTAA.repository.UserRepository;
import fr.istic.WeekendProjectTpTAA.service.JwtUserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "JSON Web Token Management System", description = "Operations pertaining to JWT security  in JWT Management System")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param authenticationRequest
     * @return POST Method for Authentication
     * @throws Exception
     */
    @RequestMapping(value = "/api/weekend/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
        throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     *
     * @param userDTO
     * @return POST Method to register a new user
     * @throws Exception
     */
    @RequestMapping(value = "/api/weekend/registerUser", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(userDTO));
    }

    @GetMapping("/hello")
    public String hellowword(){

        return "hellow word";
    }

    /**
     *
     * @return the List of Users registered in the DB
     */
    @RequestMapping(value = "/api/weekend/registerUser", method = RequestMethod.GET)
    public ResponseEntity<List<?>> findAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
