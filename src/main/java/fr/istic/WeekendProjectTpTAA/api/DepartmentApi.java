package fr.istic.WeekendProjectTpTAA.api;



import fr.istic.WeekendProjectTpTAA.exception.ResourceNotFoundException;
import fr.istic.WeekendProjectTpTAA.mappers.DepartmentMapper;
import fr.istic.WeekendProjectTpTAA.model.DTO.DepartmentDTO;
import fr.istic.WeekendProjectTpTAA.model.domain.Department;

import fr.istic.WeekendProjectTpTAA.service.DService;
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
@Api(value = "Department Management System", description = "Operations pertaining to department in Departments Management System")
    public class DepartmentApi {


            private final DService dService;

            private final DepartmentMapper departmentMapper;

            @Autowired
            public DepartmentApi(DService dService, DepartmentMapper departmentMapper) {
                this.dService = dService;
                this.departmentMapper = departmentMapper;
            }

    /**
             *
             * @return the list of departments
             */
            @GetMapping(path="/department")
            public ResponseEntity<List<DepartmentDTO>> findAll(){
                return ResponseEntity.ok(departmentMapper.toDto(dService.findAll()));
            }


            /**
             *
             * @param departmentId
             * @return a department with a particular Id
             * @throws ResourceNotFoundException
             */
            @GetMapping(path="/department/{departmentId}")
            public ResponseEntity<DepartmentDTO> findById(@PathVariable(value = "departmentId") Long departmentId)
                    throws ResourceNotFoundException {
                Optional<Department> department = Optional.ofNullable(dService.findById(departmentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId)));
                return ResponseEntity.ok(departmentMapper.toDto(department.get()));
            }


            /**
             *
             * @param departmentDTO
             * @return the items posted in a new department
             */
            @PostMapping(path = "/department", consumes = "application/json", produces = "application/json")
            public  ResponseEntity<DepartmentDTO> create( @Valid @RequestBody DepartmentDTO departmentDTO)
            {
                dService.save(departmentDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(departmentDTO);
            }



            /**
             *
             * @param departmentId
             * @param departmentDetails
             * @return a modified department
             */
            @PutMapping(path = "/department/{departmentId}", consumes = "application/json", produces = "application/json")
            public  ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable(value = "departmentId") Long departmentId,
                                                                @Valid @RequestBody DepartmentDTO departmentDetails)
            {
                Department department = departmentMapper.toDomain(departmentDetails);
                department.setDepartmentId(departmentId);
                dService.save(departmentDetails);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmentDetails);

            }


            /**
             *
             * @param departmentId
             * @return nothing
             */
            @DeleteMapping("/department/{departmentId}")
            public ResponseEntity delete(@PathVariable(value = "departmentId") Long departmentId){
                dService.deleteById(departmentId);

                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
    }