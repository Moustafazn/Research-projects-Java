/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_Utilities;

/**
 *
 * @author Moustafa
 */
public class ACompenentGeneration {
    
    public static Double getAComponent(int n_Curr)
    {
       return 2.0-((double)n_Curr*(2.0/(double)Algorithm_parameters.n_Max));
    }
}
