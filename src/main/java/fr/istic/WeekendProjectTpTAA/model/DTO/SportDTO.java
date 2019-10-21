package fr.istic.WeekendProjectTpTAA.model.DTO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SportDTO {

    private long sportId;

    @NotNull
    private String sportName;

    @NotNull
    private List<VilleDTO> villeDTOs = new ArrayList<>();


    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public List<VilleDTO> getVilleDTOs() {
        return villeDTOs;
    }

    public void setVilleDTOs(List<VilleDTO> villeDTOs) {
        this.villeDTOs = villeDTOs;
    }
}
