/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author dandani-cs
 */
public class UserController {
    // ADD instance of model
    // private UserModel userDB;
    // private int userIndex;
    // private User current_user;
    
    public UserController () {
        // userDB = new UserModel();
        
    }
    
    public boolean isAuthorized() { // pass on User object (User user)
        userIndex = getUsernames.indexOf(user.getUsername());
        if (userIndex == -1) 
            return false;
                    
        if (getPassword(userIndex) == user.getPassword()) {
            current_user = user;
            return true; 
        }
            
        
        return false;
    }
    
    public String getUserType() {
        try {
            return current_user.getUserType();
        } catch (NullPointerException e) {
            System.out.println("User is not logged in.");
            return null;
        }
    }
}
