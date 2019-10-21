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

  /**  public VilleDTO(){}
    public VilleDTO(long villeId, @NotNull String villeName, long departmentId, long regionId) {
        this.villeId = villeId;
        this.villeName = villeName;
        this.departmentId = departmentId;
        this.regionId = regionId;
    }**/

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
    /*public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }*/

    public List<SportDTO> getSportDTOS() {
        return sportDTOS;
    }

    public void setSportDTOS(List<SportDTO> sportDTOS) {
        this.sportDTOS = sportDTOS;
    }
}
