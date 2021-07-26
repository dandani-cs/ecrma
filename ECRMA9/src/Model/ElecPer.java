package Model;

import java.util.Date;

public class ElecPer {
    int elecPerId;
    String name;
    Date sdate;
    Date fdate;
    boolean archived;

    public ElecPer() {}

    public ElecPer(String name, Date sdate, Date fdate, boolean archived) {
        this.name = name;
        this.sdate = sdate;
        this.fdate = fdate;
        this.archived = archived;
    }

    public ElecPer(int elecPerId, String name, Date sdate, Date fdate, boolean archived) {
        this.elecPerId = elecPerId;
        this.name = name;
        this.sdate = sdate;
        this.fdate = fdate;
        this.archived = archived;
    }

    public int getElecPerId() {
        return elecPerId;
    }

    public void setElecPerId(int elecPerId) {
        this.elecPerId = elecPerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
   
}
