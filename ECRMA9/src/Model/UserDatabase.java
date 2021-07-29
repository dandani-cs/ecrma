/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uriel Federez
 */
public class UserDatabase {
    private static final String ERROR_MSG_FMT = "[Users DB (%s)] %s\n";
    private static ArrayList<User> users;
    private String file_name;
    
    public UserDatabase(String src_file)
    {
        this.file_name = src_file;
        this.users     = new ArrayList<>();
        read_from_file();
    }
    
    public void add_user(User user)
    {
        if(!users.contains(user))
        {
            users.add(user);
            save_to_file();
        }
        else
            System.err.printf(ERROR_MSG_FMT, "add_user", "User already exists!");
        
    }
    
    public void delete_user(User user)
    {
        if(users.contains(user)) {
            users.remove(user);
            save_to_file();
        }
            
    }
    
    public User contains_credentials(String email, String password)
    {
        for(User user : users)
        {
            if(user.get_password().equals(password) &&
               user.get_email().equals(email))
                return user;
        }
        return null;
    }
    
    public Boolean existing_email(String email) {
        for(User user : users)
        {
            if(user.get_email().equals(email))
                return true;
        }
        return false;
    }
    
    public void update_user(User user)
    {
        if(!users.contains(user))
            save_to_file();
    }
    
    private void read_from_file()
    {
        try {
            FileInputStream   ifstream = new FileInputStream(new File(file_name));
            ObjectInputStream iostream = new ObjectInputStream(ifstream); 
            
            User[] users_arr = (User[]) iostream.readObject();
            users = new ArrayList<>(Arrays.asList(users_arr));
        } catch (IOException ex) {
            System.err.printf(ERROR_MSG_FMT, "read_from_file", "File does not exist");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.printf(ERROR_MSG_FMT, "read_from_file", "readObject() error");
        }
    }
    
    private void save_to_file()
    {
        try {
            FileOutputStream   ofstream = new FileOutputStream(new File(file_name));
            ObjectOutputStream oostream = new ObjectOutputStream(ofstream); 
            
            User[] users_arr = users.toArray(new User[users.size()]);
            oostream.writeObject(users_arr);
        } catch (IOException ex) {
            System.err.printf(ERROR_MSG_FMT, "save_to_file", "File does not exist");
        }
    }
    
}
