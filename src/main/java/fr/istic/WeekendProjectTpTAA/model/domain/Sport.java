package fr.istic.WeekendProjectTpTAA.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SPORT")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SPORT_ID")
    private long sportId;

    @Column(name="SPORT_NAME", nullable=false)
    private String sportName;

    @ManyToMany(mappedBy = "sports", cascade= CascadeType.ALL )
    private List<Ville> villes = new ArrayList<Ville>();

    public Sport() {
    }

    public Sport(String sportName) {
        this.sportName = sportName;
    }


    public long getSportId() {
        return this.sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
    }


    public String getSportName() {
        return this.sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }


    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

}
