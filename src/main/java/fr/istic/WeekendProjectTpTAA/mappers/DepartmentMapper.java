package fr.istic.WeekendProjectTpTAA.mappers;


import fr.istic.WeekendProjectTpTAA.model.DTO.DepartmentDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Department;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;

//, RegionMapper.class
@Mapper(componentModel = "spring", uses = {VilleMapper.class})
public interface DepartmentMapper extends IEntityMapper<DepartmentDTO, Department>{

   @Mappings({
            @Mapping(source = "villes", target = "villeDTOs"),
            @Mapping(source = "region", target = "depRegion")
    })
    //@Mapping(source = "villes", target = "villeDTOs")
    DepartmentDTO toDto(final Department department);

    Department toDomain(final DepartmentDTO departmentDTO);

    default Department fromId(final Long departmentId){
        if (departmentId == null){
            return  null;
        }

        final Department department = new Department();
        department.setDepartmentId(departmentId);

        return department;
    }
}
