package fr.istic.WeekendProjectTpTAA.mappers;

import fr.istic.WeekendProjectTpTAA.model.DTO.VilleDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Ville;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface VilleMapper extends  IEntityMapper<VilleDTO, Ville>{


    @Mappings({
            @Mapping(source = "region", target = "villeRegion"),
            @Mapping(source = "department.departmentId", target = "departmentId"),
            @Mapping(source = "sports", target = "sportDTOS")
    })
   // @Mapping(source = "department.departmentId", target = "departmentId")
    VilleDTO toDto(final Ville ville);

    List<VilleDTO> toDto(final List<Ville> villes);

    @Mappings({
            @Mapping(source = "villeRegion", target = "region"),
            @Mapping(source = "departmentId", target = "department"),
            @Mapping(source = "sportDTOS", target = "sports")
    })
    //@Mapping(source = "departmentId", target = "department")
    Ville toDomain(final VilleDTO villeDTO);

    List<Ville> toDomain(final List<VilleDTO> villeDTOS);

    default Ville fromId(final Long villeId){
        if (villeId == null){
            return null;
        }

        final Ville ville = new Ville();
        ville.setVilleId(villeId);

        return ville;
    }
}
