/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Uriel Federez
 */
public class User implements Serializable {
    private String  email;
    private String  password;
    private Boolean is_admin;

    public User(String  email, 
                String  password, 
                Boolean is_admin) 
    {
        this.email    = email;
        this.password = password;
        this.is_admin = is_admin;
    }
    
    @Override
    public boolean equals(Object other)
    {
        return email.equals(((User)other).get_email()) &&
               password.equals(((User) other).get_password()) &&
               is_admin == ((User)other).get_is_admin();
    }
    
    public void set_email(String new_email)
    {
        this.email = new_email;
    }
    
    public void set_password(String new_password)
    {
        this.password = new_password;
    }
    
    public void set_is_admin(Boolean is_admin)
    {
        this.is_admin = is_admin;
    }
    
    public String  get_email()    { return this.email;    }
    public String  get_password() { return this.password; }
    public Boolean get_is_admin() { return this.is_admin; }
}
