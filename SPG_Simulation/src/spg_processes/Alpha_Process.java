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
public class Alpha_Process{

    private Central alpha;
    private double alpha_pos;
    private double aComponentVal;
    private int n_Curr;
    private double r1;
    private double r2;

    Alpha_Process(int n_Curr, Central alpha, double alpha_Pos, double aComponentVal) {
        this.n_Curr = n_Curr;
        this.alpha = alpha;
        this.alpha_pos = alpha_Pos;
        this.aComponentVal = aComponentVal;
        List<Double> rands = RandomGeneration.generateTwoRandNums();
        r1 = rands.get(0);
        r2 = rands.get(1);
    }

    public Central call() {
        double A1 = 2 * aComponentVal * r1 - aComponentVal;
        double C1 = 2 * r2;
        double D_alpha = Math.abs(C1 * alpha_pos - alpha.getX_val());
        double X1 = alpha_pos - A1 * D_alpha;
        alpha.setX_val(X1);
        return alpha;
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    

}
