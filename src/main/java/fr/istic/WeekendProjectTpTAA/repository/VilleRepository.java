package fr.istic.WeekendProjectTpTAA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.istic.WeekendProjectTpTAA.model.domain.Ville;


@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
   // public List<Ville> findAllVilleByDepartmentId(Long departmentId);

}
