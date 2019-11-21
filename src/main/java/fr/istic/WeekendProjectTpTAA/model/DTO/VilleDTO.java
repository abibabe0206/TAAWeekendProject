package fr.istic.WeekendProjectTpTAA.model.DTO;



import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class VilleDTO {

    private long villeId;

    @NotNull
    private String villeName;

    private long departmentId;

    @NotNull
    private RegionDTO villeRegion;

    //private long regionId;

    @NotNull
    private List<SportDTO> sportDTOS = new ArrayList<>();


    public long getVilleId() {
        return villeId;
    }

    public void setVilleId(long villeId) {
        this.villeId = villeId;
    }

    public String getVilleName() {
        return villeName;
    }

    public void setVilleName(String villeName) {
        this.villeName = villeName;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public RegionDTO getVilleRegion() {
        return villeRegion;
    }

    public void setVilleRegion(RegionDTO villeRegion) {
        this.villeRegion = villeRegion;
    }

    public List<SportDTO> getSportDTOS() {
        return sportDTOS;
    }

    public void setSportDTOS(List<SportDTO> sportDTOS) {
        this.sportDTOS = sportDTOS;
    }
}
