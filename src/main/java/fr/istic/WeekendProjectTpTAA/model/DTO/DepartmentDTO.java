package fr.istic.WeekendProjectTpTAA.model.DTO;





import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
public class DepartmentDTO {

    private long departmentId;

     @NotNull
    private String departmentName;

     @NotNull
    private List<VilleDTO> villeDTOs = new ArrayList<>();

    //@NotNull
     private RegionDTO depRegion;

    // public DepartmentDTO(){}

   /** public DepartmentDTO(long departmentId, @NotNull String departmentName, @NotNull List<VilleDTO> villeDTOs, long regionId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.villeDTOs = villeDTOs;
        this.regionId = regionId;
    }*/

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<VilleDTO> getVilleDTOs() {
        return villeDTOs;
    }

    public void setVilleDTOs(List<VilleDTO> villeDTOs) {
        this.villeDTOs = villeDTOs;
    }

    public RegionDTO getDepRegion() {
        return depRegion;
    }

    public void setDepRegion(RegionDTO depRegion) {
        this.depRegion = depRegion;
    }

    /*  public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }*/
}
