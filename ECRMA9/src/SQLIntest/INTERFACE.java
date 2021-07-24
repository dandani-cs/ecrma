package SQLIntest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class INTERFACE {
    static Scanner scan = new Scanner(System.in);
    static ENGINE engine = new ENGINE();
    public static void main(String[] args) {
        while(true) {
            cls();
            System.out.println("[1]CANDIDATES");
            System.out.println("[2]VIEW CANDIDATE & CAMPAIGN");
            System.out.print("INPUT: ");
            switch(scan.nextInt()) {
                case 1:
                    candidateMenu();
                    break;
                case 2:
                    candidateCampaign();
                    break;
                
            }
        }
    }
    
    public static void candidateMenu() {
        cls();
        System.out.println("CANDIDATES:");
        System.out.println("[0] BACK TO MAIN SCREEN");
        System.out.println("[1] ADD CANDIDATE");
        System.out.println("[2] EDIT CANDIDATE");
        System.out.println("[3] SHOW CANDIDATES");
        System.out.println("[4] NEW CANDIDATE CAMPAIGN");
        System.out.print("INPUT: ");
        int reply = scan.nextInt();
        
        if(reply == 0) {
            cls();
        }
        
        if(reply == 1) {
            cls();
            System.out.println("ENTER CANDIDATE DETAILS");
            Candidates candidates = new Candidates();
            System.out.print("lName: ");
            candidates.lName = scan.next();
            System.out.print("fName: ");
            candidates.fName = scan.next();
            candidates.mInit = ".";
            candidates.bDay.setYear(2019);
            candidates.bDay.setMonth(10);
            candidates.bDay.setDate(2);
            candidates.degree = ".";
            candidates.uni = ".";
            candidates.gradDate.setYear(2018);
            candidates.gradDate.setMonth(8);
            candidates.gradDate.setDate(3);
            candidates.picName = ".";
            
            engine.candidates.addRow(candidates);
        } 
        
        if(reply == 2) {
            cls();
            System.out.print("Enter CANDID: ");
            int id = scan.nextInt();
            Candidates candidates = engine.candidates.getRow(id);
            System.out.println("EDIT: CANDID: " + candidates.candId 
                        + " LNAME: " + candidates.lName
                        + " FNAME: " + candidates.fName + "\n");
            System.out.println("[1]LNAME");
            System.out.println("[2]FNAME");
            System.out.print("INPUT: ");
            int reply1 = scan.nextInt();
            
            if(reply1 == 1) {
                System.out.print("new LNAME: ");
                candidates.lName = scan.next();
            }
            if(reply1 == 2) {
                System.out.print("new FNAME: ");
                candidates.fName = scan.next();
            }
            
            engine.candidates.editRow(candidates);
        }
        
        if(reply == 3) {
            cls();
            System.out.println("CANDIDATES LIST\n");
            ArrayList<Candidates> candidates = engine.candidates.getTable();
            for(int i = 0; i < candidates.size(); i++)
            {
                System.out.println("CANDID: " + candidates.get(i).candId 
                        + " LNAME: " + candidates.get(i).lName
                        + " FNAME: " + candidates.get(i).fName);
            }
        }
        
        if(reply == 4) {
            cls();
            System.out.print("ENTER CANDID: ");
            int id = scan.nextInt();

            Campaigns campaigns = new Campaigns();
            campaigns.candId = id;
            System.out.print("ENTER ELECTION ID: ");
            campaigns.elecId = scan.nextInt();
            System.out.print("ENTER PARTY: ");
            campaigns.party = scan.next();
            System.out.print("ENTER POSITION: ");
            campaigns.position = scan.next();
            System.out.print("ENTER PLATFORM: ");
            campaigns.platform = scan.next();

            engine.campaigns.addRow(campaigns);
        }
    }
        
    public static void candidateCampaign() {
        cls();
        System.out.println("SHOW CANDIDATE CAMPAIGN");
        System.out.print("ENTER CANDID: ");
        int id = scan.nextInt();
        
        ArrayList<Campaigns> campaigns = engine.campaigns.getTable(id);
        for(int i = 0; i < campaigns.size(); i++) {
            System.out.println("================================================\n"
                    + "ELECTION ID: " + campaigns.get(i).elecId + "\n"
                    + "PARTY: " + campaigns.get(i).party + "\n"
                    + "POSITION: " + campaigns.get(i).position + "\n"
                    + "PLATFORM: " + campaigns.get(i).platform + "\n"
                    + "=================================================");           
        }
    }
    
    public static void cls() {
        System.out.println("\n\n\n");
    }
}
