package edu.uco.avalon.Users;


import java.util.ArrayList;
import java.util.List;

public class UserDirectory implements User {

    private List<User> userList = new ArrayList<>();

    @Override
    public int getUserCredentials(String user, String pass) {
        int returnAuth = 0;
        for(User users:userList) {
            if(users.getUserCredentials(user, pass) != 0) {
                returnAuth = users.getUserCredentials(user, pass);
                break;
            }
        }
        return returnAuth;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.add(user);
    }
}
