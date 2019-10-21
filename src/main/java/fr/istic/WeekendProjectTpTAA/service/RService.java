package fr.istic.WeekendProjectTpTAA.service;



import fr.istic.WeekendProjectTpTAA.mappers.DepartmentMapper;
import fr.istic.WeekendProjectTpTAA.mappers.RegionMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.RegionDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Department;
import fr.istic.WeekendProjectTpTAA.model.domain.Region;
import fr.istic.WeekendProjectTpTAA.repository.DepartmentRepository;
import fr.istic.WeekendProjectTpTAA.repository.RegionRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class RService {


    private final DepartmentRepository departmentRepository;
    private final RegionRepository regionRepository;
    private final DepartmentMapper departmentMapper;
    private final RegionMapper regionMapper;

    @Autowired
    public RService(DepartmentRepository departmentRepository, RegionRepository regionRepository,
                    DepartmentMapper departmentMapper, RegionMapper regionMapper) {
        this.departmentRepository = departmentRepository;
        this.regionRepository = regionRepository;
        this.departmentMapper = departmentMapper;
        this.regionMapper = regionMapper;
    }

    /**
     * to create a new region
     */
    public RegionDTO save(RegionDTO regionDTO){
        Region region = regionMapper.toDomain(regionDTO);

        Region createdRegion = regionRepository.save(region);
        List<Department> departments = new ArrayList<>();

        regionDTO.getDepartmentDTOS().forEach( departmentDTO -> {
            Department department = departmentMapper.toDomain(departmentDTO);
            department.setRegion(createdRegion);
            departments.add(department);
        });

        createdRegion.setDepartments(departments);
        departmentRepository.saveAll(departments);

        return regionMapper.toDto(createdRegion);
    }



    /**
     * to get list of region
     */
    public List<Region> findAll(){
        return regionRepository.findAll();
    }

    /**
     * find by ID
     */
    public Optional<Region> findById(Long regionId){
        return regionRepository.findById(regionId);
    }

    /**
     * deleting
     */
    public void deleteById(Long regionId){
        regionRepository.deleteById(regionId);
    }

}
