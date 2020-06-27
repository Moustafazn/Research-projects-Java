/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_Entities;

import java.util.List;

/**
 *
 * @author Moustafa
 */
public class Local_Mem {

    private int ID;
    private long timeVal;
    private double optVal;
    private double optObjVal;

    public double getOptObjVal() {
        return optObjVal;
    }

    public void setOptObjVal(double optObjVal) {
        this.optObjVal = optObjVal;
    }
    private List<PosDataVals> lcl_Monkey_positions;

    public List<PosDataVals> getLcl_Monkey_positions() {
        return lcl_Monkey_positions;
    }

    public void setLcl_Monkey_positions(List<PosDataVals> lcl_Monkey_positions) {
        this.lcl_Monkey_positions = lcl_Monkey_positions;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getTimeVal() {
        return timeVal;
    }

    public void setTimeVal(long timeVal) {
        this.timeVal = timeVal;
    }

    public double getOptVal() {
        return optVal;
    }

    public void setOptVal(double optVal) {
        this.optVal = optVal;
    }

    public void setUpdatedPosVAl(PosDataVals posVal) {
        lcl_Monkey_positions.add(posVal);
    }
    
    
}
