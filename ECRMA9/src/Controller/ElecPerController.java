package Controller;

import Model.ElecPer;
import Model.ElecPerSQL;
import java.time.LocalDate;
import java.util.Date;

public class ElecPerController {
    
    public void addElectionPeriod(FormEvent e) {
        String name = e.getElection_period().getName();
        LocalDate sDate = e.getElection_period().getSdate();
        LocalDate fDate = e.getElection_period().getFdate();

        ElecPer elecPer = new ElecPer(name, sDate, fDate, false);
        ElecPerSQL.addRow(elecPer);
    }

    public void deleteElectionPeriod(FormEvent e) {
        int elecPerId = e.getElection_period().getElecPerId();
        ElecPerSQL.deleteRow(elecPerId);
    }

    public void editElectionPeriod(FormEvent e) {
        int elecPerId = e.getElection_period().getElecPerId();
        String name = e.getElection_period().getName();
        LocalDate sDate = e.getElection_period().getSdate();
        LocalDate fDate = e.getElection_period().getFdate();
        Boolean archived = e.getElection_period().isArchived();

        ElecPer elecPer = new ElecPer(elecPerId, name, sDate, fDate, archived);
        ElecPerSQL.editRow(elecPer);
    }
}