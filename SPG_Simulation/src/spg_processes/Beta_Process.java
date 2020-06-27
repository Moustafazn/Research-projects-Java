/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_processes;

import java.util.List;
import spg_Entities.Central;
import spg_Utilities.RandomGeneration;

/**
 *
 * @author Moustafa
 */
public class Beta_Process{

    private Central beta;
    private double beta_Pos;
    private double aComponentVal;
    private int n_Curr;
    private double r1;
    private double r2;

    Beta_Process(int n_Curr, Central beta, double beta_Pos, double aComponentVal) {
        this.n_Curr = n_Curr;
        this.beta = beta;
        this.beta_Pos = beta_Pos;
        this.aComponentVal = aComponentVal;
        List<Double> rands = RandomGeneration.generateTwoRandNums();
        r1 = rands.get(0);
        r2 = rands.get(1);
    }

    public Central call() {
        double A2 = 2 * aComponentVal * r1 - aComponentVal;
        double C2 = 2 * r2;
        double D_beta = Math.abs(C2 * beta_Pos - beta.getX_val());
        double X2 = beta_Pos - A2 * D_beta;
        beta.setX_val(X2);
        return beta;
    }

    @Override
    protected void finalize() throws Throwable {
        this.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
