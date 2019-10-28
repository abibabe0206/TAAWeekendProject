package fr.istic.WeekendProjectTpTAA.repository;


import fr.istic.WeekendProjectTpTAA.model.domain.UserPpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserPplRepository extends JpaRepository<UserPpl, Integer> {
    @Query("select u from UserPpl as u where u.username = :username")
    UserPpl findByUsername(@Param("username") String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
