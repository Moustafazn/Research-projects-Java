/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_Entities;

/**
 *
 * @author Moustafa
 */
public class PosDataVals {//implements Comparable {

    private double x_val;
   // private double y_val;

//    public PosDataVals(double x_val, double y_val) {
//        this.x_val = x_val;
//        this.y_val = y_val;
//    }

    public double getX_val() {
        return x_val;
    }

    public void setX_val(double x_val) {
        this.x_val = x_val;
    }

//    public double getY_val() {
//        return y_val;
//    }
//
//    public void setY_val(double y_val) {
//        this.y_val = y_val;
//    }

//    @Override
//    public int compareTo(Object o) {
//        PosDataVals oPos = (PosDataVals) o;
//        if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
//            if (this.x_val < oPos.getX_val() && this.y_val < oPos.getY_val()) {
//                this.setX_val(oPos.getY_val());
//                this.setY_val(oPos.getY_val());
//            }
//        } else {
//            if (this.x_val > oPos.getX_val() && this.y_val > oPos.getY_val()) {
//                this.setX_val(oPos.getY_val());
//                this.setY_val(oPos.getY_val());
//            }
//        }
//        return 0;
//    }

}
