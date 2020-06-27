/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_processes.obj_Processes;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import pma_Entities.PosDataVals;
import pma_Utilities.Algorithm_parameters;
import pma_Entities.ResultObj;

/**
 *
 * @author Moustafa
 */
public class Somersault_ProcessPerObj extends RecursiveTask<ResultObj> {

    private long ObjTime;
    private PosDataVals MonkeyPositionVal;
    private double pivot;

    public Somersault_ProcessPerObj(PosDataVals MonkeyPositionVal, double posSumVal) {
        this.MonkeyPositionVal = MonkeyPositionVal;
        this.pivot = posSumVal;
    }

    private void PerformSomersaultProcessPerMonkey() {
        double tempVal = pivot - MonkeyPositionVal.getX_val();
        ObjTime = System.currentTimeMillis();
        double reaNum = ThreadLocalRandom.current().nextDouble(Algorithm_parameters.SMR_F, Algorithm_parameters.SMR_G + 1);
        ObjTime = System.currentTimeMillis();
        double alfaVAl = tempVal * reaNum;
        ObjTime = System.currentTimeMillis();
        double updatedPosValX = MonkeyPositionVal.getX_val() + alfaVAl;
        ObjTime = System.currentTimeMillis();

       if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
                if (updatedPosValX < MonkeyPositionVal.getX_val()) {
                    MonkeyPositionVal.setX_val(updatedPosValX);
                }
            } else {
                if (updatedPosValX > MonkeyPositionVal.getX_val()) {
                    MonkeyPositionVal.setX_val(updatedPosValX);
                }
            }

    }

    @Override
    protected ResultObj compute() {
        PerformSomersaultProcessPerMonkey();
        ResultObj resObj = new ResultObj();
        resObj.setPosVal(MonkeyPositionVal);
        resObj.setTimeVal(ObjTime);
        return resObj;
    }
}
