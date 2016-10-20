package ch.heigvd.amt.amtproject.rest.dto;

/**
 * Regroups user info that can be transferred on the network.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class UserDTO {
    private String username;
    private String quote;

    /**
     * Construct an empty UserDTO.
     */
    public UserDTO() {

    }

    /**
     * Constructs a PasswordUserDTO with only the username.
     *
     * @param username The user's username.
     */
    public UserDTO(String username) {
        this.username = username;
    }

    /**
     * Constructs a PasswordUserDTO with everything specified.
     *
     * @param username The user's username.
     * @param quote The user's quote.
     */
    public UserDTO(String username, String quote) {
        this.username = username;
        this.quote = quote;
    }

    public String getUsername() {
        return username;
    }

    public String getQuote() {
        return quote;
    }
}
