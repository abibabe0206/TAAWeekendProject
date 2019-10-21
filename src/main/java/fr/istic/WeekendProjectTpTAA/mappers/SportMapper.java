package fr.istic.WeekendProjectTpTAA.mappers;


import fr.istic.WeekendProjectTpTAA.model.DTO.SportDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Sport;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VilleMapper.class})
public interface SportMapper {

    @Mapping(source = "villes", target = "villeDTOs")
    SportDTO toSportDTO(Sport sport);

    List<SportDTO> toSportDTOs(List<Sport> sports);

    Sport toSport(SportDTO sportDTO);

}
