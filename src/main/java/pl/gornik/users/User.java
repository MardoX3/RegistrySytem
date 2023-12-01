package pl.gornik.users;


public class User {
    private String username;
    private String password;
    private int id;

    public User(String username, String password,int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
