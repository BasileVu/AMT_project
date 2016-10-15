package ch.heigvd.amt.amtproject.model;

public class User {
    private final String username;
    private String password;
    private String quote = "";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String quote) {
        this.username = username;
        this.password = password;
        setQuote(quote);
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
        this.quote = quote == null ? "" : quote;
    }
}
