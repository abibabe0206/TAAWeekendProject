package fr.istic.WeekendProjectTpTAA.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;

@Entity
@Table(name = "REGION")
@ApiModel(description = "All details about the Region. ")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    @ApiModelProperty(notes = "The database generated region ID")
    private long regionId;

    @Column(name = "REGION_NAME", nullable = false, length = 100)
    @ApiModelProperty(notes = "The database generated region ID")
    private String regionName;


    @ApiModelProperty(notes = "The database generated department for each region")
    @Access(AccessType.PROPERTY)
    @OneToMany(mappedBy = "region", cascade= CascadeType.ALL)
    private List<Department> departments = new ArrayList<Department>();

    @ApiModelProperty(notes = "The database generated ville for each region")
    @Access(AccessType.PROPERTY)
    @OneToMany(mappedBy = "region", cascade=CascadeType.ALL)
    private List<Ville> villes = new ArrayList<Ville>();


    public Region() {
        super();
    }

    public Region(String regionName) {
        this.regionName = regionName;
    }

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


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }


    @Override
    public String toString() {
        return "model.domain.Region [id=" + regionId + ", name=" + regionName + ", departments="
                + departments  + "]";
    }
}
