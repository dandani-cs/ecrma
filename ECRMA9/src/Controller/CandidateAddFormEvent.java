/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidate;
import java.util.EventObject;

/**
 *
 * @author Uriel Federez
 */
public class CandidateAddFormEvent extends EventObject {
    private Candidate candidate;
    
    public CandidateAddFormEvent(Object source, 
                                 Candidate candidate)
    {
        super(source);
        this.candidate = candidate;
    }
    
    public void set_candidate(Candidate candidate)
    {
        this.candidate = candidate;
    }
    
    public Candidate get_candidate()
    {
        return this.candidate;
    }
}
