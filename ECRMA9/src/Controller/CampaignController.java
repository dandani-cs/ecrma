/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Campaigns;
import Model.CampaignsSQL;

/**
 *
 * @author caeth
 */
public class CampaignController {
    
    //Components:
    //Name of Election Period - combo box (elecperid)
    //Candidate - combo box (candidateid)
    //Party - text field
    //Position - tect field
    //Platform - text area
    
    //invoking a method in the model class
    
    public void addCampaign(FormEvent e)
    {
        //set values to pass
        int candidateID = e.getCandidate().get_candidate_id();
        int elecperID = e.getElection_period().getElecPerId();
        String party = e.getCampaign().getParty();
        String position = e.getCampaign().getPosition();
        String platform = e.getCampaign().getPlatform();
        
        Campaigns campaign = new Campaigns(candidateID, elecperID, party, position, platform);
        CampaignsSQL.addRow(campaign);
    }
    
    public void deleteCampaign(FormEvent e)
    {
        //set values to pass
        int candidateID = e.getCandidate().get_candidate_id();
        int elecperID = e.getElection_period().getElecPerId();
        
        CampaignsSQL.deleteRow(candidateID, elecperID);
    }
    
    public void editCampaign(FormEvent e)
    {
        //set values to pass
        int candidateID = e.getCandidate().get_candidate_id();
        int elecperID = e.getElection_period().getElecPerId();
        String party = e.getCampaign().getParty();
        String position = e.getCampaign().getPosition();
        String platform = e.getCampaign().getPlatform();
        
        Campaigns campaign = new Campaigns(candidateID, elecperID, party, position, platform);
        CampaignsSQL.editRow(campaign);
    }
}
