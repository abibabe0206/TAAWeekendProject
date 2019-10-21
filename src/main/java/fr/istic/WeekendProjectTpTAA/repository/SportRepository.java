package fr.istic.WeekendProjectTpTAA.repository;

import fr.istic.WeekendProjectTpTAA.model.domain.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
}
