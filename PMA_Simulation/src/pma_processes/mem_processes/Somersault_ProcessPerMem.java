/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_processes.mem_processes;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import pma_Entities.Local_Mem;
import pma_Entities.PosDataVals;
import pma_Entities.ResultObj;
import pma_Utilities.Algorithm_parameters;
import pma_processes.obj_Processes.Somersault_ProcessPerObj;

/**
 *
 * @author Moustafa
 */
public class Somersault_ProcessPerMem extends RecursiveTask<Local_Mem> {

    private Local_Mem lclMem;
    private double pivot;

    public Somersault_ProcessPerMem(Local_Mem lclMem) {
        this.lclMem = lclMem;
        double posSumVal = 0;
        posSumVal = lclMem.getLcl_Monkey_positions().stream().map((val) -> val.getX_val()).reduce(posSumVal, (accumulator, _item) -> accumulator + _item);
        pivot = (1*posSumVal) / Algorithm_parameters.N_MONKEYS;
    }

    @Override
    protected Local_Mem compute() {
        double lclOptimalVal = Double.MAX_VALUE;
        while (0 < 1) {
            List<RecursiveTask<ResultObj>> forks = new LinkedList<>();
            lclMem.getLcl_Monkey_positions().stream().map((posVal)
                    -> new Somersault_ProcessPerObj(posVal, pivot)).map((Somersault_ProcessPerObj obj) -> {
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
            if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {

                if (lclOptimalVal <= lclMem.getOptVal()) {
                    lclMem.setOptVal(lclOptimalVal);
                    break;
                }
            } else {
                if (lclOptimalVal >= lclMem.getOptVal()) {
                    lclMem.setOptVal(lclOptimalVal);
                    break;
                }
            }
            lclMem.setTimeVal(System.currentTimeMillis());
        }
        return lclMem;
    }

}
