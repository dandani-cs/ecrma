package Controller;

public class ElecPerController {
    
    public void addElectionPeriod(/*CUSTOM EVENT OBJECT*/) {
        String name = e.name;
        Date sDate = e.sDate;
        Date fDate = e.fDate;

        ElecPer elecPer = new ElecPer(name, sDate, fDate, false);
        ElecPerSQL.addRow(elecPer);
    }

    public void deleteElectionPeriod(/*CUSTOM EVENT OBJECT*/) {
        int elecPerId = e.elecPerId;
        ElecPerSQL.deleteRow(elecPerId);
    }

    public void editElectionPeriod(/*CUSTOM EVENT OBJECT*/) {
        int elecPerId = e.elecPerId;
        String name = e.name;
        Date sDate = e.sDate;
        Date fDate = e.fDate;
        Boolean archived = e.archived;

        ElecPer elecPer = new ElecPer(elecPerId, name, sDate, fDate, archived);
        ElecPerSQL.editRow(elecPer);
    }
}