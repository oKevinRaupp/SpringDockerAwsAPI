package com.kevinraupp.api.studydockeraws.controller;

import com.kevinraupp.api.studydockeraws.converters.NumberConverter;
import com.kevinraupp.api.studydockeraws.exceptions.UnsuportedMathOperationException;
import com.kevinraupp.api.studydockeraws.math.SimpleMath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    private SimpleMath math = new SimpleMath();
    @GetMapping(value = "/sum/{num1}/{num2}")
    public Double sum(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.sum(NumberConverter.convertDouble(num1),NumberConverter.convertDouble(num2));
    }
    @GetMapping(value = "/sub/{num1}/{num2}")
    public Double sub(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.sub(NumberConverter.convertDouble(num1),NumberConverter.convertDouble(num2));
    }
    @GetMapping(value = "/div/{num1}/{num2}")
    public Double div(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.div(NumberConverter.convertDouble(num1),NumberConverter.convertDouble(num2));
    }
    @GetMapping(value = "/mult/{num1}/{num2}")
    public Double mult(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.mult(NumberConverter.convertDouble(num1),NumberConverter.convertDouble(num2));
    }
    @GetMapping(value = "/average/{num1}/{num2}")
    public Double average (@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.average(NumberConverter.convertDouble(num1),NumberConverter.convertDouble(num2));
    }
    @GetMapping(value = "/sqrt/{num1}")
    public Double sqrt (@PathVariable(value = "num1") String num1){
        if(!NumberConverter.isNumeric(num1)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.sqrt(NumberConverter.convertDouble(num1));
    }
}
