/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_processes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import spg_Entities.Central;
import spg_Entities.PosDataVals;
import spg_Utilities.Algorithm_parameters;
import spg_Utilities.ObjFunctions;

/**
 *
 * @author Moustafa
 */
public class Central_Process implements Callable<PosDataVals> {

    private int Id;
    private double alpha_Score;
    private double beta_Score;
    private double delta_Score;
    private double alpha_pos;
    private double beta_pos;
    private double delta_pos;
    private Central central_Pos;
    private double aComponentVal;
    private double objVal;
    private int n_Curr;

    public Central_Process(int Id, double alpha_Score, double beta_Score,
            double delta_Score, PosDataVals posVal, double aComponentVal, int n_Curr) {
        this.Id =Id;
        this.alpha_Score = alpha_Score;
        this.beta_Score = beta_Score;
        this.delta_Score = delta_Score;
        this.central_Pos =new Central();
        central_Pos.setX_val(posVal.getX_val());
        this.aComponentVal = aComponentVal;
        this.n_Curr = n_Curr;
        List<Double> values= new ArrayList<>();
        values.add(central_Pos.getX_val());
        Algorithm_parameters.fitness_vals.put(Id,ObjFunctions.applyFunction(values, Algorithm_parameters.funcName));
        //calculate obj function
    }

    public Central getCentral_Pos() {
        return central_Pos;
    }

    public void setCentral_Pos(Central central_Pos) {
        this.central_Pos = central_Pos;
    }

    public int getId() {
        return Id;
    }


    @Override
    public PosDataVals call() throws Exception {
        alpha_pos = Algorithm_parameters.alpha_Pos;
        beta_pos = Algorithm_parameters.beta_Pos;
        delta_pos = Algorithm_parameters.delta_Pos;
        Alpha_Process aplProcess = new Alpha_Process(n_Curr, central_Pos, alpha_pos, aComponentVal);
        Beta_Process betProcess = new Beta_Process(n_Curr, central_Pos, beta_pos, aComponentVal);
        Delta_Process delProcess = new Delta_Process(n_Curr, central_Pos, delta_pos, aComponentVal);
        Central x1 = aplProcess.call();
        Central x2 = betProcess.call();
        Central x3 = delProcess.call();
        double updated_Pos = (x1.getX_val() + x2.getX_val() + x3.getX_val()) / (double) 3;
        PosDataVals pos = new PosDataVals();
        pos.setX_val(updated_Pos);
        return pos;
    }
    
     @Override
    protected void finalize() throws Throwable {
        this.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
}
