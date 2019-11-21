package fr.istic.WeekendProjectTpTAA.repository;


import fr.istic.WeekendProjectTpTAA.model.domain.Role;
import fr.istic.WeekendProjectTpTAA.model.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(@Param("roleName") RoleName roleName);
}