/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pma_processes.obj_Processes;

import java.util.concurrent.RecursiveTask;
import pma_Entities.PosDataVals;
import pma_Entities.ResultObj;
import pma_Utilities.Algorithm_parameters;

/**
 *
 * @author Moustafa
 */
public class Watch_Jump_ProcessPerObj extends RecursiveTask<ResultObj>{

    private long ObjTime;
    private final PosDataVals MonkeyPositionVal;

    public Watch_Jump_ProcessPerObj(PosDataVals MonkeyPositionVal) {
        this.MonkeyPositionVal = MonkeyPositionVal;
    }
    
    @Override
    protected ResultObj compute() {
        double updatedPosVal = MonkeyPositionVal.getX_val() - Algorithm_parameters.EYESIGHT_B;
        Algorithm_parameters.EYESIGHT_B = Math.random();
        ResultObj resObj = new ResultObj();
        PosDataVals tempObj = new PosDataVals();
        tempObj.setX_val(updatedPosVal);
        resObj.setPosVal(tempObj);
        ObjTime = System.currentTimeMillis();
        resObj.setTimeVal(ObjTime);
        return resObj;
    }
}
