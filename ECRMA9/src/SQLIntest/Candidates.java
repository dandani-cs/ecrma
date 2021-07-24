/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLIntest;

import java.util.Date;

/**
 *
 * @author apmbonifacio
 */
public class Candidates {
    
    public int candId;
    public String lName;
    public String fName;
    public String mInit;
    public Date bDay;
    public String degree;
    public String uni;
    public Date gradDate;
    public String picName;

    public Candidates(){
    this.bDay = new Date();
    this.gradDate = new Date();
    };
    
    public Candidates(int candId, String lName, String fName, String mInit, Date bDay, String degree, String uni, Date gradDate, String picName) {
        this.candId = candId;
        this.lName = lName;
        this.fName = fName;
        this.mInit = mInit;
        this.bDay = bDay;
        this.degree = degree;
        this.uni = uni;
        this.gradDate = gradDate;
        this.picName = picName;
    }
    
    
}
