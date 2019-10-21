package fr.istic.WeekendProjectTpTAA.model.jwtModel;


import java.io.Serializable;

/**
 * this class is used for creating the response containing the JWT to be returned to the client
 */
public class JwtResponse  implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
