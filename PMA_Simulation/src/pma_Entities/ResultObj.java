/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_Entities;

/**
 *
 * @author Moustafa
 */
public class ResultObj {

    private long timeVal;

    public long getTimeVal() {
        return timeVal;
    }

    public void setTimeVal(long timeVal) {
        this.timeVal = timeVal;
    }

    public PosDataVals getPosVal() {
        return posVal;
    }

    public void setPosVal(PosDataVals posVal) {
        this.posVal = posVal;
    }
    private PosDataVals posVal;

}
