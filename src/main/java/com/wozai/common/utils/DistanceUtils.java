package com.wozai.common.utils;

import static com.wozai.common.utils.MathExt.*;
import static java.lang.Math.*;
/**
 * Created by zengzihao on 2014/4/10.
 */
public class DistanceUtils {
    public static final double R = 6370996.81;

    public static double getDistance(Double lat1,Double lng1,Double lat2,Double lng2){
        return R*arccos(cos(lat1*pi()/180 )*cos(lat2*pi()/180)*cos(lng1*pi()/180 -lng2*pi()/180)+
                sin(lat1*pi()/180 )*sin(lat2*pi()/180));
    }

    public static double pi(){
       return 3.14;
    }

    public static void main(String args[]){
        System.out.println(getDistance(118.726459,32.211878,118.727011,32.212063));
    }
}
