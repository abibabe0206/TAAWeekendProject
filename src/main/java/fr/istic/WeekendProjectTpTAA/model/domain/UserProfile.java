package fr.istic.WeekendProjectTpTAA.model.domain;


import fr.istic.WeekendProjectTpTAA.service.UserPrinciple;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERPROFILE")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String userRegion;


    @NotBlank
    @Size(min = 3, max = 50)
    private String userDepartment;

    @NotBlank
    @Size(min = 3, max = 50)
    private String userVille;

    @NotBlank
    @Size(min = 3, max = 50)
    private String userSport;

    @NotBlank
    @Size(min = 3, max = 50)
    private String userPet;

    @NotBlank
    @Size(min = 3, max = 50)
    private String userFood;

    //
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserPpl userProfilePpl;

    public UserProfile() {
        super();
    }


    public UserProfile( String userRegion, String userDepartment, String userVille,
                       String userSport, String userPet, String userFood) {


        this.userRegion = userRegion;
        this.userDepartment = userDepartment;
        this.userVille = userVille;
        this.userSport = userSport;
        this.userPet = userPet;
        this.userFood = userFood;
    }

    public UserProfile( String userRegion, String userDepartment, String userVille,
                       String userSport, String userPet, String userFood,
                       UserPpl userProfilePpl) {


        this.userRegion = userRegion;
        this.userDepartment = userDepartment;
        this.userVille = userVille;
        this.userSport = userSport;
        this.userPet = userPet;
        this.userFood = userFood;
        this.userProfilePpl = userProfilePpl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserVille() {
        return userVille;
    }

    public void setUserVille(String userVille) {
        this.userVille = userVille;
    }

    public String getUserSport() {
        return userSport;
    }

    public void setUserSport(String userSport) {
        this.userSport = userSport;
    }

    public String getUserPet() {
        return userPet;
    }

    public void setUserPet(String userPet) {
        this.userPet = userPet;
    }

    public String getUserFood() {
        return userFood;
    }

    public void setUserFood(String userFood) {
        this.userFood = userFood;
    }

    public UserPpl getUserProfilePpl() {
        return userProfilePpl;
    }

    public void setUserProfilePpl(UserPpl userProfilePpl) {
        this.userProfilePpl = userProfilePpl;
    }

}
