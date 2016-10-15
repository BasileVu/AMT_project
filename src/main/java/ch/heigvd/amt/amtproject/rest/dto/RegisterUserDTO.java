package ch.heigvd.amt.amtproject.rest.dto;

public class RegisterUserDTO {
    private  String username;
    private  String password;

    public RegisterUserDTO() {

    }

    public RegisterUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
