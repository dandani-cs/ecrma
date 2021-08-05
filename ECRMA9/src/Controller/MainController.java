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
public class MainController {
    public UserController user_controller;
    public ElecPerController elecper_controller;
    public CampaignController campaign_controller;
    public CandidateController candidate_controller;
    
    public MainController () {
        campaign_controller = new CampaignController();
        user_controller = new UserController();
        elecper_controller = new ElecPerController();
        candidate_controller = new CandidateController();
    }
}
