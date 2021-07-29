/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Model.UserDatabase;

/**
 *
 * @author dandani-cs
 */
public class UserController {
    private UserDatabase userDB;
    private User current_user;
    
    public UserController () {
        userDB = new UserDatabase("users.txt");
    }
    
    public boolean isAuthorized(String email, String password) { // pass on User object (User user)
        User attempt = userDB.contains_credentials(email, password);
        
        if (attempt != null) {
            current_user = attempt;
            return true;
        }    
        return false;
    }
    
    public Boolean isAdmin() {
        try {
            return current_user.get_is_admin();
        } catch (NullPointerException e) {
            System.out.println("User is not logged in.");
            return null;
        }
    }
    
    public void logout() {
        current_user = null;
    }
    
    public String signup(String email, String password, String confirm_password) {
        if (!password.equals(confirm_password)) {
            return "Passwords do not match";
        }
        
        if (userDB.existing_email(email)) {
            return "There is a record with this email.";
        }
        
        User new_user = new User(email, password, false);
        userDB.add_user(new_user);
        return "Sign up successful";
    }
}
