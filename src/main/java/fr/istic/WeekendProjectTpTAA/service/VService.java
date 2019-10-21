package fr.istic.WeekendProjectTpTAA.service;


import fr.istic.WeekendProjectTpTAA.mappers.VilleMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.VilleDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Ville;
import fr.istic.WeekendProjectTpTAA.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VService {

    private final VilleRepository villeRepository;
    private final VilleMapper villeMapper;

    @Autowired
    public VService(VilleRepository villeRepository, VilleMapper villeMapper) {
        this.villeRepository = villeRepository;
        this.villeMapper = villeMapper;
    }

    /**
     * to create a new ville
     */
    public VilleDTO save(VilleDTO villeDTO){
        Ville ville = villeMapper.toDomain(villeDTO);

        Ville createdVille = villeRepository.save(ville);

        createdVille.setDepartment(ville.getDepartment());
        createdVille.setRegion(ville.getRegion());
        createdVille.setSports(ville.getSports());


        return villeMapper.toDto(createdVille);
    }


    /**
     * to get list of department
     */
    public List<Ville> findAll(){
        return villeRepository.findAll();
    }

    /**
     * find by ID
     */
    public Optional<Ville> findById(Long villeId){
        return villeRepository.findById(villeId);
    }

    /**
     * deleting
     */
    public void deleteById(Long villeId){
        villeRepository.deleteById(villeId);
    }

}
