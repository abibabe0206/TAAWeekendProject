package fr.istic.WeekendProjectTpTAA.repository;

import fr.istic.WeekendProjectTpTAA.model.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{
}
