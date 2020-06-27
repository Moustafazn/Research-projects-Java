/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_processes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import spg_Entities.PosDataVals;
import spg_Utilities.ACompenentGeneration;
import spg_Utilities.Algorithm_parameters;
import spg_Utilities.ObjFunctions;

/**
 *
 * @author Moustafa
 */
public class Input_Process {

    private List<PosDataVals> wolves_positions;
    private Dispatcher_Process dispatcher;

    public Input_Process() {
        Map<String, Double> minMaxValues = ObjFunctions.getMinMaxValues(Algorithm_parameters.funcName);
        double min = minMaxValues.get("min");
        double max = minMaxValues.get("max");
        wolves_positions = new ArrayList<>();
        for (int i = 0; i < Algorithm_parameters.no_Of_WOLVES; i++) {
            PosDataVals posVal = new PosDataVals();
            posVal.setX_val(ThreadLocalRandom.current().nextDouble(min, max + 1));
            wolves_positions.add(posVal);
        }

    }

    public Input_Process(List<PosDataVals> wolves_positions) {
        this.wolves_positions = wolves_positions;
    }

    public void performInputProcess() throws InterruptedException, ExecutionException {

        dispatcher = new Dispatcher_Process(wolves_positions, ACompenentGeneration.getAComponent(Algorithm_parameters.n_Curr));
           Algorithm_parameters.fitness_vals.clear();
        dispatcher.performDispatcherProcess();
    }

}
