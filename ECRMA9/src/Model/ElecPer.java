package Model;

import java.time.LocalDate;
import java.util.Date;

public class ElecPer {
    int elecPerId;
    String name;
    LocalDate sdate;
    LocalDate fdate;
    boolean archived;

    public ElecPer() {}

    public ElecPer(String name, LocalDate sdate, LocalDate fdate, boolean archived) {
        this.name = name;
        this.sdate = sdate;
        this.fdate = fdate;
        this.archived = archived;
    }

    public ElecPer(int elecPerId, String name, LocalDate sdate, LocalDate fdate, boolean archived) {
        this.elecPerId = elecPerId;
        this.name = name;
        this.sdate = sdate;
        this.fdate = fdate;
        this.archived = archived;
    }
    
    public String toString()
    {
        return name;
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

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public LocalDate getFdate() {
        return fdate;
    }

    public void setFdate(LocalDate fdate) {
        this.fdate = fdate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
   
}
