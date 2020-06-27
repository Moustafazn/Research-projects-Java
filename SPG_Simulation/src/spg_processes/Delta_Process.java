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
public class Delta_Process{

    private Central delta;
    private double delta_Pos;
    private double r1;
    private double r2;
    private double aComponentVal;
    private int n_Curr;

    Delta_Process(int n_Curr, Central delta, double delta_Pos, double aComponentVal) {
        this.n_Curr = n_Curr;
        this.delta = delta;
        this.delta_Pos = delta_Pos;
        this.aComponentVal = aComponentVal;
        List<Double> rands = RandomGeneration.generateTwoRandNums();
        r1 = rands.get(0);
        r2 = rands.get(1);
    }

    public Central call() {
        double A3 = 2 * aComponentVal * r1 - aComponentVal;
        double C3 = 2 * r2;
        double D_delta = Math.abs(C3 * delta_Pos - delta.getX_val());
        double X1 = delta_Pos - A3 * D_delta;
        delta.setX_val(X1);
        return delta;
    }
    
     @Override
    protected void finalize() throws Throwable {
        this.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

}
