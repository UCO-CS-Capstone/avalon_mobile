package edu.uco.avalon.Users;


public class Administrator implements User {
    private String username;
    private String password;
    private static long id = 1;
    private final int flag = 1;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
        this.id++;
    }

    @Override
    public int getUserCredentials(String user, String pass) {
        if(this.username.equals(user) && this.password.equals(pass))
            return this.flag;
        else return 0;
    }
}
