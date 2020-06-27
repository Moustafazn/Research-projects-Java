/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_simulation;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spg_Utilities.Algorithm_parameters;
import spg_processes.Input_Process;

/**
 *
 * @author Moustafa
 */
public class SPG_Simulation {

    public static int no_of_runtime = 20;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Algorithm_parameters.setAlgorithm_parameters(100, 100, 1, Double.MAX_VALUE,
                    Double.MAX_VALUE, Double.MAX_VALUE, "func1", "min");
            Input_Process in_pro = new Input_Process();
            in_pro.performInputProcess();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SPG_Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
