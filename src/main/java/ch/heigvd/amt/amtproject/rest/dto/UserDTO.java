package ch.heigvd.amt.amtproject.rest.dto;

public class UserDTO {
    private String username;
    private String quote;

    public UserDTO() {

    }

    public UserDTO(String username) {
        this.username = username;
    }

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
