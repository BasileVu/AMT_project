package ch.heigvd.amt.amtproject.rest.dto;

/**
 * Regroups user info that is transferred on the network.
 * Used when handling info coming from outside.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class PasswordUserDTO extends UserDTO {
    private String password;

    /**
     * Construct an empty PasswordUserDTO.
     */
    public PasswordUserDTO() {
        super();
    }

    /**
     * Constructs a PasswordUserDTO with everything except the user's quote.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    public PasswordUserDTO(String username, String password) {
        super(username);
        this.password = password;
    }

    /**
     * Constructs a PasswordUserDTO with everything specified.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param quote The user's quote.
     */
    public PasswordUserDTO(String username, String password, String quote) {
        super(username, quote);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
