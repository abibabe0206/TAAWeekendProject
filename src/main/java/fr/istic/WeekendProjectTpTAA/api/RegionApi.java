package fr.istic.WeekendProjectTpTAA.api;



import fr.istic.WeekendProjectTpTAA.exception.ResourceNotFoundException;
import fr.istic.WeekendProjectTpTAA.mappers.RegionMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.RegionDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Region;
import fr.istic.WeekendProjectTpTAA.service.RService;
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
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/api/weekend") or
@RequestMapping(path = "/api/weekend", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Region Management System", description = "Operations pertaining to region in Regions Management System")
public class RegionApi {


     private final RService rService;

     private final RegionMapper regionMapper;

     @Autowired
    public RegionApi(RService rService, RegionMapper regionMapper) {
        this.rService = rService;
        this.regionMapper = regionMapper;
    }

    /**
     *
     * @return the list of regions
     */
    @GetMapping(path="/region")
    public ResponseEntity<List<RegionDTO>> findAll(){
        return ResponseEntity.ok(regionMapper.toDto(rService.findAll()));
    }


    /**
     *
     * @param regionId
     * @return a region with a particular Id
     * @throws ResourceNotFoundException
     */
    @GetMapping(path="/region/{regionId}")
    public ResponseEntity<RegionDTO> findById(@PathVariable(value = "regionId") Long regionId)
            throws ResourceNotFoundException {
        Optional<Region> region = Optional.ofNullable(rService.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found for this id :: " + regionId)));
        return ResponseEntity.ok(regionMapper.toDto(region.get()));
    }


    /**
     *
     * @param regionDTO
     * @return the items posted in a new region
     */
    @PostMapping(path = "/region", consumes = "application/json", produces = "application/json")
    public  ResponseEntity<RegionDTO> create( @Valid @RequestBody RegionDTO regionDTO)
    {
        rService.save(regionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(regionDTO);
    }



    /**
     *
     * @param regionId
     * @param regionDetails
     * @return a modified region
     */
    @PutMapping(path = "/region/{regionId}", consumes = "application/json", produces = "application/json")
    public  ResponseEntity<RegionDTO> updateRegion(@PathVariable(value = "regionId") Long regionId,
                                                           @Valid @RequestBody RegionDTO regionDetails)
    {
        Region region = regionMapper.toDomain(regionDetails);
        region.setRegionId(regionId);
        rService.save(regionDetails);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(regionDetails);

    }


    /**
     *
     * @param regionId
     * @return nothing
     */
    @DeleteMapping("/regionId/{regionId}")
    public ResponseEntity delete(@PathVariable(value = "regionId") Long regionId){
        rService.deleteById(regionId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
