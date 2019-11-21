package fr.istic.WeekendProjectTpTAA.api;

import fr.istic.WeekendProjectTpTAA.exception.ResourceNotFoundException;

import fr.istic.WeekendProjectTpTAA.mappers.VilleMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.VilleDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Ville;
import fr.istic.WeekendProjectTpTAA.service.VService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;



import java.util.List;
import java.util.Optional;
import javax.validation.Valid;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/api/weekend") or
@RequestMapping(path = "/api/info/weekend", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@Api(value = "Ville Management System", description = "Operations pertaining to ville in Ville Management System")
    public class VilleApi {

            private final VService vService;
            private final VilleMapper villeMapper;

            @Autowired
            public VilleApi(VService vService, VilleMapper villeMapper) {
                this.vService = vService;
                this.villeMapper = villeMapper;
            }

            /**
             *
             * @return the list of villes
             */
            @GetMapping(path="/ville")
            public ResponseEntity<List<VilleDTO>> findAll(){
                return ResponseEntity.ok(villeMapper.toDto(vService.findAll()));
            }


            /**
             *
             * @param villeId
             * @return a ville with a particular Id
             * @throws ResourceNotFoundException
             */
            @GetMapping(path="/ville/{villeId}")
            public ResponseEntity<VilleDTO> findById(@PathVariable(value = "villeId") Long villeId)
                    throws ResourceNotFoundException {
                Optional<Ville> ville = Optional.ofNullable(vService.findById(villeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Ville not found for this id :: " + villeId)));
                return ResponseEntity.ok(villeMapper.toDto(ville.get()));
            }


            /**
             *
             * @param villeDTO
             * @return the items posted in a new ville
             */
            @PostMapping(path = "/ville", consumes = "application/json", produces = "application/json")
            public  ResponseEntity<VilleDTO> create( @Valid @RequestBody VilleDTO villeDTO)
            {
                vService.save(villeDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(villeDTO);
            }



            /**
             *
             * @param villeId
             * @param villeDetails
             * @return a modified ville
             */
            @PutMapping(path = "/ville/{villeId}", consumes = "application/json", produces = "application/json")
            public  ResponseEntity<VilleDTO> updateVille(@PathVariable(value = "villeId") Long villeId,
                                                                   @Valid @RequestBody VilleDTO villeDetails)
            {
                Ville ville = villeMapper.toDomain(villeDetails);
                ville.setVilleId(villeId);
                vService.save(villeDetails);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(villeDetails);

            }


            /**
             *
             * @param villeId
             * @return nothing
             */
            @DeleteMapping("/ville/{villeId}")
            public ResponseEntity delete(@PathVariable(value = "villeId") Long villeId){
                vService.deleteById(villeId);

                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
    }
