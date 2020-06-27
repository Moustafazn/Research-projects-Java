/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_processes;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import spg_Entities.PosDataVals;
import spg_Utilities.Algorithm_parameters;
import static spg_Utilities.Algorithm_parameters.updated_positions;
import spg_Utilities.ObjFunctions;
import spg_Utilities.SortFunction;

/**
 *
 * @author Moustafa
 */
public class Dispatcher_Process {

    private List<PosDataVals> wolves_positions;
    private List<Future<PosDataVals>> new_Wolves_positions;
    private double aComponentVal;
    private ForkJoinPool forkJoinPool;

    public Dispatcher_Process(List<PosDataVals> wolves_positions, double aComponentVal) {
        this.wolves_positions = wolves_positions;
        this.aComponentVal = aComponentVal;
    }

    public void performDispatcherProcess() throws InterruptedException, ExecutionException {
        List<Central_Process> central_op = new LinkedList<>();
        for (int i = 0; i < wolves_positions.size(); i++) {
            Central_Process central = new Central_Process(i, Algorithm_parameters.alpha_Score,
                    Algorithm_parameters.beta_Score, Algorithm_parameters.delta_Score, wolves_positions.get(i),
                    aComponentVal, Algorithm_parameters.n_Curr);
            central_op.add(central);
        }
        Algorithm_parameters.updated_positions.clear();
        Algorithm_parameters.positions_history.add(central_op.get(0).getCentral_Pos());
        SortFunction.sort_and_index();
       for(int i=0; i< Algorithm_parameters.fitness_vals.size();i++){
            if (Algorithm_parameters.fitness_vals.get(i) == Algorithm_parameters.alpha_Score) {
                Algorithm_parameters.alpha_Pos = central_op.get(i).getCentral_Pos().getX_val();
                ObjFunctions.convergence_Curve.add(central_op.get(i).getCentral_Pos().getX_val());
            } else if (Algorithm_parameters.fitness_vals.get(i) == Algorithm_parameters.beta_Score) {
                Algorithm_parameters.beta_Pos = central_op.get(i).getCentral_Pos().getX_val();
            } else if (Algorithm_parameters.fitness_vals.get(i) == Algorithm_parameters.delta_Score) {
                Algorithm_parameters.delta_Pos = central_op.get(i).getCentral_Pos().getX_val();
            }
       }
        forkJoinPool = new ForkJoinPool();
        try {
            new_Wolves_positions = forkJoinPool.invokeAll(central_op);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
        for (Future<PosDataVals> pos : new_Wolves_positions) {
            updated_positions.add(pos.get());
        }
        Output_Process.ValidateIteration();
    }
}
