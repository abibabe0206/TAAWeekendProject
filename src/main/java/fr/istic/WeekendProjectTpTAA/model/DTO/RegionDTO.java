package fr.istic.WeekendProjectTpTAA.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class RegionDTO {

    private long regionId;

    @NotNull
    private String regionName;

    @NotNull
    private List<DepartmentDTO> departmentDTOS = new ArrayList<>();

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<DepartmentDTO> getDepartmentDTOS() {
        return departmentDTOS;
    }

    public void setDepartmentDTOS(List<DepartmentDTO> departmentDTOS) {
        this.departmentDTOS = departmentDTOS;
    }
}
