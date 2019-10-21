package fr.istic.WeekendProjectTpTAA.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


import io.swagger.annotations.*;

@Entity
@Table(name = "VILLE")
@ApiModel(description = "All details about the Villes. ")
public class Ville{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VILLE_ID")
    @ApiModelProperty(notes = "The database generated ville ID")
    private long villeId;

    @Column(name = "VILLE_NAME", nullable = false, length = 100)
    @ApiModelProperty(notes = "The database generated ville's name")
    private String villeName;


    @ApiModelProperty(notes = "The database generated the department for each ville")
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;


    @ApiModelProperty(notes = "The database generated the region for each ville")
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "REGION_ID")
    private Region region;


    /**
     *  each city will have a unique sport and whether, this
     *  is why am using HashSet and not list
     */
    @ApiModelProperty(notes = "The database generated ville and sport table")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VILLE_SPORT", joinColumns = { @JoinColumn(name = "VILLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "SPORT_ID") })
    private Set<Sport> sports = new HashSet<Sport>(0);




    public Ville() {
        super();
    }


    public Ville(String villeName, Department department, Region region) {
        this.villeName = villeName;
        this.department = department;
        this.region = region;
    }

    public Ville(String villeName, Department department, Region region, Set<Sport> sports) {
        this.villeName = villeName;
        this.department = department;
        this.region = region;
        this.sports = sports;
    }



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


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


    public Set<Sport> getSports() {
        return this.sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }


    @Override
    public String toString() {
        return "model.domain.Ville [id=" + villeId + ", name=" + villeName + ", department="
                + department.getDepartmentName() + "]";
    }

}