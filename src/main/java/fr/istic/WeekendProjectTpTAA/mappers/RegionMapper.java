package fr.istic.WeekendProjectTpTAA.mappers;



import fr.istic.WeekendProjectTpTAA.model.DTO.RegionDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Region;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface RegionMapper extends  IEntityMapper<RegionDTO, Region>{

    @Mapping(source = "departments", target = "departmentDTOS")
    RegionDTO toDto(final Region region);

    Region toDomain(final RegionDTO regionDTO);

    default Region fromId(final Long regionId){
        if (regionId == null){
            return  null;
        }

        final Region region = new Region();
        region.setRegionId(regionId);

        return region;
    }
}
