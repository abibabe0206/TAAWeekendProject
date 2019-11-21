package fr.istic.WeekendProjectTpTAA.repository;

import fr.istic.WeekendProjectTpTAA.model.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
