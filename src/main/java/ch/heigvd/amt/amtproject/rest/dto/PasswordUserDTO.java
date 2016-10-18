package ch.heigvd.amt.amtproject.rest.dto;

public class PasswordUserDTO extends UserDTO {
    private String password;

    public PasswordUserDTO() {
        super();
    }

    public PasswordUserDTO(long id, String username, String password) {
        super(id, username);
        this.password = password;
    }

    public PasswordUserDTO(long id, String username, String password, String quote) {
        super(id, username, quote);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
