package fr.istic.WeekendProjectTpTAA.model.domain;


import javax.persistence.*;

@Entity
@Table(name = "USER")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    public Users(){
        super();
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
