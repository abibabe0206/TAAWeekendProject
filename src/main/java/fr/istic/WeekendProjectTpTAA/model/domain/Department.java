package fr.istic.WeekendProjectTpTAA.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


import io.swagger.annotations.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DEPARTMENT")
@ApiModel(description = "All details about the Department. ")
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GenericGenerator(
            name = "native",
            strategy = "native"
    )*/
    @Column(name = "DEPARTMENT_ID")
    @ApiModelProperty(notes = "The database generated department ID")
    private long departmentId;

    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 100)
    @ApiModelProperty(notes = "The database generated department name")
    private String departmentName;

    @Access(AccessType.PROPERTY)
    @OneToMany(mappedBy = "department", cascade= CascadeType.ALL)
    @ApiModelProperty(notes = "The database generated ville for each department")
    private List<Ville> villes = new ArrayList<Ville>();


    @ApiModelProperty(notes = "The database generated the region of each department")
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "REGION_ID")
    //@JsonIgnore
    private Region region;

    public Department() {
        super();
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(String departmentName, Region region) {
        this.departmentName = departmentName;
        this.region = region;
    }

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



    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }



    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    @Override
    public String toString() {
        return "model.domain.Department [id=" + departmentId + ", name=" + departmentName + ", villes=" + villes +
                ", region=" +"]";// region.getRegionName() + "]";
    }

}
