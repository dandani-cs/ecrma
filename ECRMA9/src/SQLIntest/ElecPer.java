/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLIntest;

import java.util.Date;

/**
 *
 * @author apmbonifacio
 */
public class ElecPer {
    int elecId;
    int name;
    Date sDate;
    Date fDate;
    Boolean archive;

    public ElecPer(int elecId, int name, Date sDate, Date fDate, Boolean archive) {
        this.elecId = elecId;
        this.name = name;
        this.sDate = sDate;
        this.fDate = fDate;
        this.archive = archive;
    }
}
