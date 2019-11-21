package fr.istic.WeekendProjectTpTAA.service;

import fr.istic.WeekendProjectTpTAA.mappers.DepartmentMapper;
import fr.istic.WeekendProjectTpTAA.mappers.RegionMapper;
import fr.istic.WeekendProjectTpTAA.mappers.VilleMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.DepartmentDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Department;
import fr.istic.WeekendProjectTpTAA.model.domain.Region;
import fr.istic.WeekendProjectTpTAA.model.domain.Ville;
import fr.istic.WeekendProjectTpTAA.repository.DepartmentRepository;
import fr.istic.WeekendProjectTpTAA.repository.RegionRepository;
import fr.istic.WeekendProjectTpTAA.repository.VilleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DService {

    private final DepartmentRepository departmentRepository;
    private final VilleRepository villeRepository;
    private final DepartmentMapper departmentMapper;
    private final VilleMapper villeMapper;
    private final RegionMapper regionMapper;
    private final RegionRepository regionRepository;

    @Autowired
    public DService(DepartmentRepository departmentRepository, VilleRepository villeRepository,
                    DepartmentMapper departmentMapper, VilleMapper villeMapper,
                    RegionMapper regionMapper, RegionRepository regionRepository) {
        this.departmentRepository = departmentRepository;
        this.villeRepository = villeRepository;
        this.departmentMapper = departmentMapper;
        this.villeMapper = villeMapper;
        this.regionMapper = regionMapper;
        this.regionRepository = regionRepository;
    }

    /**
     * to create a new department
     */
    public DepartmentDTO save(DepartmentDTO departmentDTO){
        Department department = departmentMapper.toDomain(departmentDTO);

        Department createdDepartment = departmentRepository.save(department);
        List<Ville> villes = new ArrayList<>();

        departmentDTO.getVilleDTOs().forEach( villeDTO -> {
           Ville ville = villeMapper.toDomain(villeDTO);
           ville.setDepartment(createdDepartment);
           villes.add(ville);
        });

        departmentDTO.getDepRegion();

        createdDepartment.setVilles(villes);
        villeRepository.saveAll(villes);

        return departmentMapper.toDto(createdDepartment);
    }



    /**
     * to get list of department
     */
    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    /**
     * find by ID
     */
    public Optional<Department> findById(Long departmentId){
        return departmentRepository.findById(departmentId);
    }

    /**
     * deleting
     */
    public void deleteById(Long departmentId){
        departmentRepository.deleteById(departmentId);
    }
}
