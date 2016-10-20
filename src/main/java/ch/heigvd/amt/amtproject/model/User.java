package ch.heigvd.amt.amtproject.model;

/**
 * Represents an user of the website.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class User {
    private final long id;
    private final String username;
    private String password;
    private String quote = "";

    /**
     *  Constructs the user.
     * @param id The id of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param quote The quote defined by the user.
     */
    public User(long id, String username, String password, String quote) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
