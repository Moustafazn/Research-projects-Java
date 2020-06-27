/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_running;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pma_Utilities.Algorithm_parameters;
import pma_Utilities.FileUtils;
import pma_processes.Global_Mem_Operations;

/**
 *
 * @author Moustafa
 */
public class Main_Run {

    public static List<Double> xyzGlobal = new ArrayList<Double>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            for (int m = 0; m < 50; m++) {
                Algorithm_parameters.setAlgorithm_parameters(10, 100, 0.000001,
                        1, -1, 1, 2183333, 30, 500, 100, -11111789.8034758448287, "func1", "min");
                Global_Mem_Operations glblMemOps = new Global_Mem_Operations(startTime);
                glblMemOps.performInitializationProcess();
                xyzGlobal.add(glblMemOps.global_solution);
            }
            System.out.println("list: " + xyzGlobal);
            FileUtils.writeValuesInFile(xyzGlobal, "fitnessAfter50Run "+Algorithm_parameters.funcName);
        } catch (Exception ex) {
            Logger.getLogger(Main_Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
