/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLIntest;

/**
 *
 * @author apmbonifacio
 */
public class Campaigns {
    int camId;
    int candId;
    int elecId;
    String party;
    String position;
    String platform;

    public Campaigns() {}
    
    public Campaigns(int camId, int candId, int elecId, String party, String position, String platform) {
        this.camId = camId;
        this.candId = candId;
        this.elecId = elecId;
        this.party = party;
        this.position = position;
        this.platform = platform;
    }
}