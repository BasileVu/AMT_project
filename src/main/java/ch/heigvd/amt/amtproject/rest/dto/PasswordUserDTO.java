package ch.heigvd.amt.amtproject.rest.dto;

public class PasswordUserDTO extends UserDTO {
    private String password;

    public PasswordUserDTO() {
        super();
    }

    public PasswordUserDTO(String username, String password) {
        super(username);
        this.password = password;
    }

    public PasswordUserDTO(String username, String password, String quote) {
        super(username, quote);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
