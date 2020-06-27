/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spg_Entities.Central;
import spg_Entities.PosDataVals;

/**
 *
 * @author Moustafa
 */
public class Algorithm_parameters {

    public static int no_Of_WOLVES;
    public static int n_Max;
    public static int dim;
    public static int n_Curr;
    public static double alpha_Score;
    public static double beta_Score;
    public static double delta_Score;
    public static double alpha_Pos;
    public static double beta_Pos;
    public static double delta_Pos;
    public static double obj_funcVal;
    public static List<PosDataVals> updated_positions;
    public static List<Central> positions_history;
    public static List<Double> xyzGlobal = new ArrayList<Double>();
    public static void setPositions_history(Central centralVal) {
        positions_history .add(centralVal);
    }
    public static Map<Integer,Double> fitness_vals;
    public static String funcType = "min";
    public static String funcName = "func1";

    public static void setAlgorithm_parameters(int no_Of_WOLVES, int n_Max, int dim, double alpha_Score, double beta_Score,
            double delta_Score, String funcName, String funcType) {
        Algorithm_parameters.no_Of_WOLVES = no_Of_WOLVES;
        Algorithm_parameters.n_Max = n_Max;
        Algorithm_parameters.dim = dim;
        Algorithm_parameters.alpha_Score = alpha_Score;
        Algorithm_parameters.beta_Score = beta_Score;
        Algorithm_parameters.delta_Score = delta_Score;
        Algorithm_parameters.funcName = funcName;
        Algorithm_parameters.funcType = funcType;
        n_Curr = 1;
        updated_positions = new ArrayList<>(no_Of_WOLVES);
        fitness_vals = new HashMap<>(no_Of_WOLVES);
        positions_history = new ArrayList<>();
    }
}
