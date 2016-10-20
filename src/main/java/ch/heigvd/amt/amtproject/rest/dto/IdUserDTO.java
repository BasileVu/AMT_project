package ch.heigvd.amt.amtproject.rest.dto;

/**
 * Regroups registered user info that can be transferred on the network.
 * Used when displaying a registered user's public info.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class IdUserDTO extends UserDTO {
    private long id;

    /**
     * Construct an empty IdUserDTO.
     */
    public IdUserDTO() {
        super();
    }

    /**
     * Constructs an IdUserDTO with everything except the user's quote.
     *
     * @param id The user's id.
     * @param username The user's username.
     */
    public IdUserDTO(long id, String username) {
        super(username);
        this.id = id;
    }

    /**
     * Constructs an IdUserDTO with everything specified.
     * @param id The user's id.
     * @param username The user's username.
     * @param quote The user's quote.
     */
    public IdUserDTO(long id, String username, String quote) {
        super(username, quote);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
