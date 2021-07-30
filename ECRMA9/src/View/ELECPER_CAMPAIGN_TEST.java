/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import java.util.Date;

/**
 *
 * @author apmbonifacio
 */
public class ELECPER_CAMPAIGN_TEST {
    public static void main(String[] args) {
//        CAMPAIGNS
//        Campaigns campaign = new Campaigns();
////        add row
//        campaign = new Campaigns(1,2,"PARTY3","POSITION3","PLATFORM3");
//        CampaignsSQL.addRow(campaign);
//        
////        edit row
//        campaign = new Campaigns(1,1,"PARTYEDIT1","POSITIONEDIT1","PLATFORMEDIT1");
//        CampaignsSQL.editRow(campaign);
//        
////        delete row
//        campaign = new Campaigns(2,2,"PARTY2","POSITION2","PLATFORM2");
//        CampaignsSQL.addRow(campaign);
//        CampaignsSQL.deleteRow(2, 2);
//        
//        //get row
//        Object[] campaignobj = CampaignsSQL.getRow(1, 1);
//        System.out.println("campaignobj election name: " + campaignobj[0].toString());
//        System.out.println("campaignobj party: " + campaignobj[1].toString());
//        System.out.println("campaignobj position: " + campaignobj[2].toString());
//        System.out.println("campaignobj platform: " + campaignobj[3].toString());
//        
//        //get rows
//        Object[][] campaignobjs = CampaignsSQL.getRows(1);
//        for(int i = 0; i < campaignobjs.length; i++) {
//            System.out.println(
//                    campaignobjs[i][0].toString() + ", " 
//                    + campaignobjs[i][1].toString() + ", " 
//                    + campaignobjs[i][2].toString() + ", " 
//                    + campaignobjs[i][3].toString());
////        }


        //ELECPER
//        add row
        Date sDate = new Date(1999,11,3);
        Date fDate = new Date(2000,12,4);
        
        ElecPer elecPer = new ElecPer("old", sDate, fDate, false);
        ElecPerSQL.addRow(elecPer);
        
//        edit row
        sDate = new Date(1998,5,6);
        fDate = new Date(1999,6,7);
        elecPer = new ElecPer(2,"older", sDate, fDate, false);    //elecPerID is needed
        ElecPerSQL.editRow(elecPer);
        
//        delete row
        ElecPerSQL.deleteRow(6);
        
        //get row
        Object[] elecper2 = ElecPerSQL.getRow(1);
        System.out.println("name: " + elecper2[0]);
        System.out.println("sDate: " + elecper2[1]);
        System.out.println("fDate: " + elecper2[2]);
        System.out.println("archived: " + elecper2[3]);

        //get table
        Object[][] elecper = ElecPerSQL.getTable();
        for(int i = 0; i < elecper.length; i++) {
            System.out.println(
                    elecper[i][0].toString() + ", " 
                    + elecper[i][1].toString() + ", " 
                    + elecper[i][2].toString() + ", " 
                    + elecper[i][3].toString());
        }        
    }
}
