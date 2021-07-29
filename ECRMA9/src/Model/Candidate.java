/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Uriel Federez
 */
public class Candidate {

    private int    candidate_id;
    private String last_name;
    private String first_name;
    private char   middle_initial;
    private Date   birth_date;
    private String religion;
    private String degree;
    private String university;
    private Date   grad_date;
    private String image_path;
    
    public Candidate()
    {
    }

    public Candidate(String last_name, 
                     String first_name, 
                     char   middle_initial, 
                     Date   birth_date,
                     String religion,
                     String degree, 
                     String university, 
                     Date   grad_date, 
                     String image_path) 
    {
        this.last_name      = last_name;
        this.first_name     = first_name;
        this.middle_initial = middle_initial;
        this.birth_date     = birth_date;
        this.religion       = religion;
        this.degree         = degree;
        this.university     = university;
        this.grad_date      = grad_date;
        this.image_path     = image_path;
    }
    
    public void set_candidate_id(int new_id)
    {
        this.candidate_id = new_id;
    }
    
    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }

    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }

    public void set_middle_initial(char middle_initial) {
        this.middle_initial = middle_initial;
    }

    public void set_birth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void set_religion(String religion) {
        this.religion = religion;
    }

    public void set_degree(String degree) {
        this.degree = degree;
    }

    public void set_university(String university) {
        this.university = university;
    }

    public void set_grad_date(Date grad_date) {
        this.grad_date = grad_date;
    }

    public void set_image_path(String image_path) {
        this.image_path = image_path;
    }
    
    
    public int    get_candidate_id() { return candidate_id;   }
    public String get_last_name()    { return last_name;      }
    public String get_first_name()   { return first_name;     }
    public char   get_mid_initial()  { return middle_initial; }
    public Date   get_birth_date()   { return birth_date;     }
    public String get_religion()     { return religion;       }
    public String get_degree()       { return degree;         }
    public String get_university()   { return university;     }
    public Date   get_grad_date()    { return grad_date;      }
    public String get_image_path()   { return image_path;     }
}
