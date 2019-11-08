package fr.istic.WeekendProjectTpTAA.repository;


import fr.istic.WeekendProjectTpTAA.model.domain.UserPpl;
import fr.istic.WeekendProjectTpTAA.model.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

   @Query("SELECT u FROM UserProfile u where u.userProfilePpl.username = :userName")
   UserProfile findByUserName(@Param("userName") String userName);

   @Query("select p from UserPpl p where username = :username")
   UserPpl getUser(String username);

}
