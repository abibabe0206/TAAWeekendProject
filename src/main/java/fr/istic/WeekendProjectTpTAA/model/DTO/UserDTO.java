package fr.istic.WeekendProjectTpTAA.model.DTO;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private long userId;

    @NotNull
    private String username;

    @NotNull
    private  String password;

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
