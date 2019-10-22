package fr.istic.WeekendProjectTpTAA.repository;


import fr.istic.WeekendProjectTpTAA.model.DTO.UserDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("select u from Users as u where u.username = :username")
    Users findByUsername(@Param("username") String username);
}
