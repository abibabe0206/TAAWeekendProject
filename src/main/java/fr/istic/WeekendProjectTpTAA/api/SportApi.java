package fr.istic.WeekendProjectTpTAA.api;

import fr.istic.WeekendProjectTpTAA.exception.ResourceNotFoundException;


import fr.istic.WeekendProjectTpTAA.mappers.SportMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.SportDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Sport;
import fr.istic.WeekendProjectTpTAA.service.SService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;



import java.util.List;
import java.util.Optional;
import javax.validation.Valid;



@RestController
//@RequestMapping("/api/weekend") or
@RequestMapping(path = "/api/weekend", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Sport Management System", description = "Operations pertaining to sport in Sport Management System")
public class SportApi {


   private final SService sService;

   private final SportMapper sportMapper;

   @Autowired
    public SportApi(SService sService, SportMapper sportMapper) {
        this.sService = sService;
        this.sportMapper = sportMapper;
    }

    /**
     *
     * @return the list of sports
     */
    @GetMapping(path="/sport")
    public ResponseEntity<List<SportDTO>> findAll() {
        return ResponseEntity.ok(sportMapper.toSportDTOs(sService.findAll()));
    }


    /**
     *
     * @param sportId
     * @return a sport with a particular Id
     * @throws ResourceNotFoundException
     */
    @GetMapping(path="/sport/{sportId}")
    public ResponseEntity<SportDTO> findById(@PathVariable(value = "sportId") Long sportId) {
        Optional<Sport> sport = sService.findById(sportId);

        return ResponseEntity.ok(sportMapper.toSportDTO(sport.get()));
    }


    /**
     *
     * @param sportDTO
     * @return the items posted in a new sport
     */
    @PostMapping(path = "/sport", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SportDTO> create(@RequestBody SportDTO sportDTO) {
        sService.save(sportMapper.toSport(sportDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(sportDTO);
    }



    /**
     *
     * @param sportId
     * @param sportDetails
     * @return a modified sport
     */
    @PutMapping(path = "/sport/{sportId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SportDTO> updateSport(@PathVariable(value = "sportId") Long sportId,
                                           @Valid @RequestBody SportDTO sportDetails) {
        Sport sport = sportMapper.toSport(sportDetails);
        sport.setSportId(sportId);

        sService.save(sport);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sportDetails);
    }


    /**
     *
     * @param sportId
     * @return nothing
     */
    @DeleteMapping("/sport/{sportId}")
    public ResponseEntity delete(@PathVariable(value = "sportId") Long sportId) {
        sService.deleteById(sportId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}