package Model;

public class Campaigns {
    private int campaignId;
    private int candidateId;
    private int elecPerId;
    private String party;
    private String position;
    private String platform;

    public Campaigns() {}

    public Campaigns(int candidateId, int elecPerId, String party, String position, String platform) {
        this.candidateId = candidateId;
        this.elecPerId = elecPerId;
        this.party = party;
        this.position = position;
        this.platform = platform;
    }

    public Campaigns(int campaignId, int candidateId, int elecPerId, String party, String position, String platform) {
        this.campaignId = campaignId;
        this.candidateId = candidateId;
        this.elecPerId = elecPerId;
        this.party = party;
        this.position = position;
        this.platform = platform;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getElecPerId() {
        return elecPerId;
    }

    public void setElecPerId(int elecPerId) {
        this.elecPerId = elecPerId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
}
