/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_Utilities;

/**
 *
 * @author Moustafa
 */
public class Algorithm_parameters {

    public static int M_LOCAL_MEMBS;
    public static int N_MONKEYS;
    public static double STEP_LENGTH_L;
    public static double EYESIGHT_B;
    public static double SMR_F;
    public static double SMR_G;
    public static long T_MAX;
    public static int DIMENSION_d;
    public static int P_C;
    public static int N_MAX;
    public static double desired_solution;
    public static String funcType = "min";
    public static String funcName = "func1";

    public static void setAlgorithm_parameters(int M_LOCAL_MEMBS, int N_MONKEYS, double STEP_LENGTH, double EYESIGHT_B,
            double SMR_F, double SMR_G, long T_MAX, int DIMENSION_d, int P_C, int N_MAX, double desired_solution, String funcName, String funcType) {
        Algorithm_parameters.M_LOCAL_MEMBS = M_LOCAL_MEMBS;
        Algorithm_parameters.N_MONKEYS = N_MONKEYS;
        Algorithm_parameters.STEP_LENGTH_L = STEP_LENGTH;
        Algorithm_parameters.EYESIGHT_B = EYESIGHT_B;
        Algorithm_parameters.SMR_F = SMR_F;
        Algorithm_parameters.SMR_G = SMR_G;
        Algorithm_parameters.DIMENSION_d = DIMENSION_d;
        Algorithm_parameters.P_C = P_C;
        Algorithm_parameters.N_MAX = N_MAX;
        Algorithm_parameters.desired_solution = desired_solution;
        Algorithm_parameters.funcName = funcName;
        Algorithm_parameters.funcType = funcType;
    }

}
