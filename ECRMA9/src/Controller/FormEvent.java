/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Campaigns;
import Model.Candidate;
import Model.ElecPer;
import Model.User;
import java.util.EventObject;

/**
 *
 * @author dandani-cs
 */
public class FormEvent extends EventObject {
    private int num;
    private String text;
    private User user;
    private Candidate candidate;
    private ElecPer election_period;
    private Campaigns campaign;
    private String purpose;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public FormEvent(Object source){
        super(source);
    }

    public FormEvent(Object source, int num) {
        super(source);
        this.num = num;
    }

    public FormEvent(Object source, String text) {
        super(source);
        this.text = text;
    }

    public FormEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public FormEvent(Object source, Candidate candidate) {
        super(source);
        this.candidate = candidate;
    }

    public FormEvent(Object source, ElecPer election_period) {
        super(source);
        this.election_period = election_period;
    }

    public FormEvent(Object source, Campaigns campaign) {
        super(source);
        this.campaign = campaign;
    }
    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public ElecPer getElection_period() {
        return election_period;
    }

    public void setElection_period(ElecPer election_period) {
        this.election_period = election_period;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }
}
