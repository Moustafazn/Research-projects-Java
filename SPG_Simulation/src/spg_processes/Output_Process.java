/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_processes;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spg_Utilities.Algorithm_parameters;
import spg_Utilities.FileUtils;
import spg_Utilities.ObjFunctions;
import spg_simulation.SPG_Simulation;
import static spg_simulation.SPG_Simulation.no_of_runtime;

/**
 *
 * @author Moustafa
 */
public class Output_Process {
    
    private static Input_Process input_Process;
    
    public static void ValidateIteration() throws InterruptedException, ExecutionException {
        if (Algorithm_parameters.n_Curr >= Algorithm_parameters.n_Max) {
            Algorithm_parameters.xyzGlobal.add(Collections.max(ObjFunctions.convergence_Curve));
            System.out.println("global_solution is:" + Collections.max(ObjFunctions.convergence_Curve));
            FileUtils.writeCentralValuesInFile(Algorithm_parameters.positions_history, "Monkey_positions (" + Algorithm_parameters.funcName + ")");
            Algorithm_parameters.positions_history.clear();
            FileUtils.writeValuesInFile(ObjFunctions.convergence_Curve, "Convergence_curve (" + Algorithm_parameters.funcName + ")");
            ObjFunctions.convergence_Curve.clear();
            FileUtils.writeValuesInFile(ObjFunctions.fitness_History, "Fitness_History (" + Algorithm_parameters.funcName + ")");
            ObjFunctions.fitness_History.clear();
            resetAlgorithmRuntime();
        } else {
            Algorithm_parameters.n_Curr++;
            input_Process = new Input_Process(Algorithm_parameters.updated_positions);
            input_Process.performInputProcess();
            System.runFinalization();
        }
    }
    
    private static void resetAlgorithmRuntime() {
        try {
            no_of_runtime--;
            if (no_of_runtime > 0) {
                Algorithm_parameters.setAlgorithm_parameters(100, 100, 1, Double.MAX_VALUE,
                        Double.MAX_VALUE, Double.MAX_VALUE, Algorithm_parameters.funcName, Algorithm_parameters.funcType);
                Input_Process in_pro = new Input_Process();
                in_pro.performInputProcess();
                
            }
            if (no_of_runtime == 0) {
                FileUtils.writeValuesInFile(Algorithm_parameters.xyzGlobal, "fitnessAfter20Run " + Algorithm_parameters.funcName);
                System.exit(1);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SPG_Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
