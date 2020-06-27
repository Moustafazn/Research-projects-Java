/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_processes;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import pma_Entities.Local_Mem;
import pma_Entities.PosDataVals;
import pma_Utilities.Algorithm_parameters;
import pma_Utilities.FileUtils;
import pma_Utilities.ObjFunctions;
import pma_processes.mem_processes.Climb_ProcessPerMem;
import pma_processes.mem_processes.Somersault_ProcessPerMem;

/**
 *
 * @author Moustafa
 */
public class Global_Mem_Operations {

    private static double min;
    private static double max;
    private List<PosDataVals> Monkey_positions;
    private List<PosDataVals> positions_history;
    private List<Future<Local_Mem>> local_Mems;
    private List<Climb_ProcessPerMem> climb_Process_Mems;
    private int threshold;
    private final long start_time;
    private long global_time;
    public double global_solution;
    public double global_ObjVal = Double.MAX_VALUE;
    private double optFitnessVal;
    private int numOfIter = 0;
    private ForkJoinPool forkJoinPool = null;

    public Global_Mem_Operations(long startTime) {
         Map<String, Double> minMaxValues = ObjFunctions.getMinMaxValues(Algorithm_parameters.funcName);
        min = minMaxValues.get("min");
        max = minMaxValues.get("max");
        Monkey_positions = new ArrayList<>();
        for (int i = 0; i < Algorithm_parameters.N_MONKEYS; i++) {
            PosDataVals posVal=  new PosDataVals();
            posVal.setX_val(ThreadLocalRandom.current().nextDouble(min, max + 1));
            Monkey_positions.add(posVal);
        }
        positions_history = new ArrayList<>();
        start_time = startTime;
    }

    List<Future<Local_Mem>> callClimbProcess() {
        forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invokeAll(climb_Process_Mems);
    }

    public List<PosDataVals> getMonkey_positions() {
        return Monkey_positions;
    }

    public void performInitializationProcess() {
        positions_history.add(Monkey_positions.get(0));
        threshold = Algorithm_parameters.N_MONKEYS / Algorithm_parameters.M_LOCAL_MEMBS;
        climb_Process_Mems = new LinkedList<>();
        int valIndex = 0;
        for (int i = 0; i < Algorithm_parameters.M_LOCAL_MEMBS; i++) { ////////////parallel
            Local_Mem lclMem = new Local_Mem();
            lclMem.setID(i);
            List<PosDataVals> local_Monkey_positions = new ArrayList<PosDataVals>();
            for (int j = 0; j < threshold; j++) { ////////////parallel
                local_Monkey_positions.add(Monkey_positions.get(j + valIndex));
            }
            valIndex += threshold;
            lclMem.setLcl_Monkey_positions(local_Monkey_positions);
            Climb_ProcessPerMem singleMem = new Climb_ProcessPerMem(lclMem);
            climb_Process_Mems.add(singleMem);

        }
        try {
            local_Mems = callClimbProcess();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
        optFitnessVal = climb_Process_Mems.get(0).getObjVal();
        for (int k = 1; k < climb_Process_Mems.size(); k++) {
            if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
                if (optFitnessVal > climb_Process_Mems.get(k).getObjVal()) {
                    optFitnessVal = climb_Process_Mems.get(k).getObjVal();
                }
            } else {
                if (optFitnessVal < climb_Process_Mems.get(k).getObjVal()) {
                    optFitnessVal = climb_Process_Mems.get(k).getObjVal();
                }
            }
        }
        performTheRestOfProcessses();
    }

    public void performTheRestOfProcessses() {
        try {
            Migration_Precedure migration = new Migration_Precedure(local_Mems);
            migration.performMigrationProcess();
            Somersault_ProcessPerMem somersault = new Somersault_ProcessPerMem(migration.getLclMem());
            Local_Mem membrane = null;
            try {
                forkJoinPool = new ForkJoinPool();
                membrane = forkJoinPool.invoke(somersault);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                forkJoinPool.shutdown();
            }
            Monkey_positions = membrane.getLcl_Monkey_positions();
            performTerminationProcess(membrane.getOptVal(), membrane.getOptObjVal());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void performTerminationProcess(double optimal_solution, double objVal) {
        global_solution = Double.MAX_VALUE;
        global_time = new Date().getTime();
        numOfIter++;
        if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
            if (global_solution > optimal_solution) {
                global_solution = optimal_solution;
            }
        } else {
            if (global_solution < optimal_solution) {
                global_solution = optimal_solution;
            }
        }
        if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
            if (global_ObjVal > objVal) {
                global_ObjVal = objVal;
            }
        } else {
            if (global_ObjVal < objVal) {
                global_ObjVal = objVal;
            }
        }
        long time = global_time - start_time;
        //time >= Algorithm_parameters.T_MAX || global_solution <= Algorithm_parameters.desired_solution ||      
        if (numOfIter >= Algorithm_parameters.N_MAX) {
            System.out.println("global_solution is:" + global_solution);
            //System.out.println("global_ObjVal is:" + global_ObjVal);
            System.out.println("Time consumption is:" + time + " ms");
            System.out.println("Max Number of iterations is:" + numOfIter);
            FileUtils.writeValuesListInFile(positions_history, "Monkey_positions ("+Algorithm_parameters.funcName+")");
            FileUtils.writeValuesListInFile(ObjFunctions.convergence_Curve, "Convergence_curve ("+Algorithm_parameters.funcName+")");
            ObjFunctions.convergence_Curve.clear();
            FileUtils.writeValuesInFile(ObjFunctions.fitness_History, "Fitness_History ("+Algorithm_parameters.funcName+")");
            ObjFunctions.fitness_History.clear();
//             try {
//                Runtime.getRuntime().exec("your matlab function");
//            } catch (IOException ex) {
//                Logger.getLogger(Global_Mem_Operations.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            PosDataVals gloVal = new PosDataVals();
            gloVal.setX_val(global_solution);
            ObjFunctions.convergence_Curve.add(gloVal);
            ObjFunctions.fitness_History.add(optFitnessVal);
            performInitializationProcess();
        }
    }

}
