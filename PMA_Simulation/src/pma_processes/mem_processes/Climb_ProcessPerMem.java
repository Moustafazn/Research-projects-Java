/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_processes.mem_processes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;
import pma_Entities.Local_Mem;
import pma_Entities.PosDataVals;
import pma_Entities.ResultObj;
import pma_Utilities.Algorithm_parameters;
import pma_Utilities.ObjFunctions;
import pma_processes.obj_Processes.Climb_ProcessPerObj;

/**
 *
 * @author Moustafa
 */
public class Climb_ProcessPerMem implements Callable<Local_Mem> {

    private final Local_Mem lclMem;
    private double ObjVal;

    //parallelism between objects
    public Climb_ProcessPerMem(Local_Mem lclMem) {
        this.lclMem = lclMem;
    }

    @Override
    public Local_Mem call() throws Exception {
        double lclOptimalVal = Double.MAX_VALUE;
        for (int i = 0; i < Algorithm_parameters.P_C; i++) {////////////parallel
            claculateObjFunc();
            List<RecursiveTask<ResultObj>> forks = new LinkedList<>();
            lclMem.getLcl_Monkey_positions().stream().map((posVal)
                    -> new Climb_ProcessPerObj(posVal, ObjVal)).map((Climb_ProcessPerObj obj) -> {
                        /*posVal*/ forks.add(obj);
                        return obj;
                    }).forEach((obj) -> {
                        obj.fork();
                    });
            lclMem.getLcl_Monkey_positions().clear();
            for (RecursiveTask<ResultObj> task : forks) {
                ResultObj resObjVal = task.join();
                if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {

                    if (lclOptimalVal > resObjVal.getPosVal().getX_val()) {
                        lclOptimalVal = resObjVal.getPosVal().getX_val();
                    }
                } else {
                    if (lclOptimalVal < resObjVal.getPosVal().getX_val()) {
                        lclOptimalVal = resObjVal.getPosVal().getX_val();
                    }
                }
                lclMem.setUpdatedPosVAl(resObjVal.getPosVal());
            }
            lclMem.setOptVal(lclOptimalVal);
            lclMem.setTimeVal(System.currentTimeMillis());
        }
        return lclMem;
    }

    private void claculateObjFunc() {
        List<PosDataVals> tempSumVal = new ArrayList<>(lclMem.getLcl_Monkey_positions().size());
        List<PosDataVals> tempSubVal = new ArrayList<>(lclMem.getLcl_Monkey_positions().size());
        double randNum = Math.random();
        double deltaRand, tempObjVal;
        if (randNum >= 1 / 2) {
            deltaRand = Algorithm_parameters.STEP_LENGTH_L;
        } else {
            deltaRand = -Algorithm_parameters.STEP_LENGTH_L;
        }
        for (PosDataVals val : lclMem.getLcl_Monkey_positions()) {
            PosDataVals subPos = new PosDataVals();
            PosDataVals sumPos = new PosDataVals();
            sumPos.setX_val(deltaRand + val.getX_val());
            subPos.setX_val(deltaRand - val.getX_val());
            tempSumVal.add(sumPos);
            tempSubVal.add(subPos);
        }
        tempObjVal = ObjFunctions.applyFunction(tempSumVal, Algorithm_parameters.funcName)
                - ObjFunctions.applyFunction(tempSubVal, Algorithm_parameters.funcName);
        ObjVal = tempObjVal / (2 * deltaRand);
    }

    public double getObjVal() {
        return ObjVal;
    }

}
