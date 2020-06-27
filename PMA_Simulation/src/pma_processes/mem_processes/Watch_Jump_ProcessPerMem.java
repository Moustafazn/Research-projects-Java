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
import pma_processes.obj_Processes.Watch_Jump_ProcessPerObj;

/**
 *
 * @author Moustafa
 */
/**
 *
 * @author Moustafa
 */
public class Watch_Jump_ProcessPerMem implements Callable<Local_Mem> {

    private final Local_Mem lclMem;
    private double optFitnessVal;

    public double getOptFitnessVal() {
        return optFitnessVal;
    }

    public Watch_Jump_ProcessPerMem(Local_Mem lclMem) {
        this.lclMem = lclMem;
    }

    @Override
    public Local_Mem call() throws Exception {
        double lclOptimalVal = Double.MAX_VALUE;
        optFitnessVal = ObjFunctions.applyFunction(lclMem.getLcl_Monkey_positions(), Algorithm_parameters.funcName);
        List<PosDataVals> currentPosList = lclMem.getLcl_Monkey_positions();
        List<RecursiveTask<ResultObj>> forks = new LinkedList<>();
        lclMem.getLcl_Monkey_positions().stream().map((posVal)
                -> new Watch_Jump_ProcessPerObj(posVal)).map((Watch_Jump_ProcessPerObj obj) -> {
                    /*posVal*/ forks.add(obj);
                    return obj;
                }).forEach((obj) -> {
                    obj.fork();
                });
        lclMem.getLcl_Monkey_positions().clear();
        forks.stream().map((task) -> task.join()).forEach((resObjVal) -> {
            lclMem.setUpdatedPosVAl(resObjVal.getPosVal());
        });
        double updatedObjVal = ObjFunctions.applyFunction(lclMem.getLcl_Monkey_positions(),
                Algorithm_parameters.funcName);
        List<PosDataVals> updatedPosList = new ArrayList<>();
            if (optFitnessVal > updatedObjVal) {
                updatedPosList.addAll(currentPosList);
                optFitnessVal = updatedObjVal;
            } else {
                updatedPosList.addAll(lclMem.getLcl_Monkey_positions());
            }
          for (int i = 0; i < lclMem.getLcl_Monkey_positions().size(); i++) {
       
            if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {

                if (lclOptimalVal > lclMem.getLcl_Monkey_positions().get(i).getX_val()) {
                    lclOptimalVal = lclMem.getLcl_Monkey_positions().get(i).getX_val();
                }
            } else {
                if (lclOptimalVal < lclMem.getLcl_Monkey_positions().get(i).getX_val()) {
                    lclOptimalVal = lclMem.getLcl_Monkey_positions().get(i).getX_val();
                }
            }
        }
        lclMem.setLcl_Monkey_positions(updatedPosList);
        lclMem.setOptVal(lclOptimalVal);
        lclMem.setOptObjVal(optFitnessVal);
        lclMem.setTimeVal(System.currentTimeMillis());
        return lclMem;
    }
}
