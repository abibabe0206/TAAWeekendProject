package fr.istic.WeekendProjectTpTAA.api;


import fr.istic.WeekendProjectTpTAA.exception.ResourceNotFoundException;
import fr.istic.WeekendProjectTpTAA.model.domain.UserPpl;
import fr.istic.WeekendProjectTpTAA.model.domain.UserProfile;
import fr.istic.WeekendProjectTpTAA.model.jwtModel.ResponseMessage;
import fr.istic.WeekendProjectTpTAA.repository.UserProfileRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/info/weekend", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('USER')")
@Api(value = "UserProfile Management System", description = "Operations pertaining to a user's profile in the application")
public class UserProfileApi {

    @Autowired
    UserProfileRepository userProfileRepository;


   /*@PostMapping(path = "/userProfile", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> profile(@Valid @RequestBody UserProfile userProfile) {


        // Creating User's Profile Account
        UserProfile userProfile1 = new UserProfile(userProfile.getUserRegion(), userProfile.getUserDepartment(),
                userProfile.getUserVille(), userProfile.getUserSport(), userProfile.getUserPet(), userProfile.getUserFood());

       userProfileRepository.save(userProfile1);


        return new ResponseEntity<>(new ResponseMessage("User's Profile registered successfully!"), HttpStatus.OK);
    }*/

    @GetMapping("/userProfile/id/{id}")
    public UserProfile getUserProfileById(@PathVariable("id") Long id)
    {
        UserProfile userProfile = userProfileRepository.findById(id).get();

        return userProfile;
    }


   @PostMapping(path = "/userProfile/{username}", consumes = "application/json", produces = "application/json")
   public UserProfile createProfile(@PathVariable(value = "username") String username,
                                    @RequestBody UserProfile userProfile){
       UserPpl userPpl = userProfileRepository.getUser(username);
       userProfile.setUserProfilePpl(userPpl);

       userProfileRepository.save(userProfile);

       return getUserProfileById(userProfile.getId());
   }

    @GetMapping(value = "/userProfile")
    public ResponseEntity<List<?>> findAllUsersProfile(){
        return ResponseEntity.ok(userProfileRepository.findAll());
    }


    @GetMapping(path="/userProfile/{userName}")
    public ResponseEntity<UserProfile> findByUserName(@PathVariable(value = "userName") String userName) throws ResourceNotFoundException {

        UserProfile uProfile = userProfileRepository.findByUserName(userName);
                //.orElseThrow(() -> new ResourceNotFoundException("UserProfile not found for this id :: " + userId));
        return ResponseEntity.ok().body(uProfile);
    }


    @PutMapping("/userProfile/{id}")
    public ResponseEntity<UserProfile> updateProfile(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody UserProfile profileDetails) throws ResourceNotFoundException {
        UserProfile uProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found for this id :: " + id));


        uProfile.setUserRegion(profileDetails.getUserRegion());
        uProfile.setUserDepartment(profileDetails.getUserDepartment());
        uProfile.setUserVille(profileDetails.getUserVille());
        uProfile.setUserSport(profileDetails.getUserSport());
        uProfile.setUserPet(profileDetails.getUserPet());
        uProfile.setUserFood(profileDetails.getUserFood());
        final UserProfile updatedProfile = userProfileRepository.save(uProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/userProfile/{id}")
    public Map<String, Boolean> deleteProfile(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        UserProfile userPro = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        userProfileRepository.delete(userPro);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
