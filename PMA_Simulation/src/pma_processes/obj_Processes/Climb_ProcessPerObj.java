/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_processes.obj_Processes;

import java.util.concurrent.RecursiveTask;
import pma_Entities.PosDataVals;
import pma_Utilities.Algorithm_parameters;
import pma_Entities.ResultObj;

/**
 *
 * @author Moustafa
 */
public class Climb_ProcessPerObj extends RecursiveTask<ResultObj> {

    //private double deltaRand;
    private long localTime;
    private PosDataVals MonkeyPositionVal;
    private final double objVal;

    public Climb_ProcessPerObj(PosDataVals MonkeyPositionVal, double objVal) {
        this.MonkeyPositionVal = MonkeyPositionVal;
        this.objVal = objVal;
    }

    private void PerformClimbProcessPerMonkey() {
            int tempSignVal = generateSignVal(objVal);
            localTime = System.currentTimeMillis();
            double signVal = Algorithm_parameters.STEP_LENGTH_L * tempSignVal;
            localTime = System.currentTimeMillis();
            double updatedPosValX = signVal + MonkeyPositionVal.getX_val();
            localTime = System.currentTimeMillis();

            if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
                if (updatedPosValX < MonkeyPositionVal.getX_val()) {
                    MonkeyPositionVal.setX_val(updatedPosValX);
                }
            } else {
                if (updatedPosValX > MonkeyPositionVal.getX_val()) {
                    MonkeyPositionVal.setX_val(updatedPosValX);
                }
            }
            localTime = System.currentTimeMillis();
        
    }

    private int generateSignVal(double posVal) {
        if (posVal == 0) {
            return 0;
        } else if (posVal > 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public long getLocalTime() {
        return localTime;
    }

    public PosDataVals getMonkeyPositionVal() {
        return MonkeyPositionVal;
    }

    @Override
    public ResultObj compute() {
        PerformClimbProcessPerMonkey();
        ResultObj resObj = new ResultObj();
        resObj.setPosVal(MonkeyPositionVal);
        resObj.setTimeVal(localTime);
        return resObj;
    }

}
