package ch.heigvd.amt.amtproject.rest.dto;

public class UserDTO {
    private long id;
    private String username;
    private String quote;

    public UserDTO() {

    }

    public UserDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(long id, String username, String quote) {
        this.id = id;
        this.username = username;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getQuote() {
        return quote;
    }
}
