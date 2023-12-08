package com.kevinraupp.api.studydockeraws.math;

import com.kevinraupp.api.studydockeraws.converters.NumberConverter;
public class SimpleMath {
    public Double sum(Double num1,Double num2){
        return num1 + num2;
    }
    public Double sub(Double num1,Double num2){
        return num1 - num2;
    }
    public Double div(Double num1,Double num2){
        return num1 / num2;
    }
    public Double mult(Double num1,Double num2){
        return num1 * num2;
    }
    public Double average (Double num1, Double num2){
        return (num1 + num2) / 2;
    }
    
    public Double sqrt (Double num1){
        return Math.sqrt(num1);
    }
}
