package fr.istic.WeekendProjectTpTAA.service;


import fr.istic.WeekendProjectTpTAA.model.domain.Sport;
import fr.istic.WeekendProjectTpTAA.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SService {

    private final SportRepository sportRepository;

    @Autowired
    public SService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public List<Sport> findAll() {
        return sportRepository.findAll();
    }

    public Optional<Sport> findById(Long id) {
        return sportRepository.findById(id);
    }

    public Sport save(Sport sport) {
        return sportRepository.save(sport);
    }

    public void deleteById(Long sportId) {
        sportRepository.deleteById(sportId);
    }
}
