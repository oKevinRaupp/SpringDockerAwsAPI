package com.kevinraupp.api.studydockeraws.controller;

import com.kevinraupp.api.studydockeraws.exceptions.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @GetMapping(value = "/sum/{num1}/{num2}")
    public Double sum(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!isNumeric(num1) || !isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return convertDouble(num1) + convertDouble(num2);
    }
    @GetMapping(value = "/sub/{num1}/{num2}")
    public Double sub(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!isNumeric(num1) || !isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return convertDouble(num1) - convertDouble(num2);
    }

    @GetMapping(value = "/div/{num1}/{num2}")
    public Double div(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!isNumeric(num1) || !isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return convertDouble(num1) / convertDouble(num2);
    }
    @GetMapping(value = "/mult/{num1}/{num2}")
    public Double mult(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!isNumeric(num1) || !isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return convertDouble(num1) * convertDouble(num2);
    }

    @GetMapping(value = "/average/{num1}/{num2}")
    public Double average (@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!isNumeric(num1) || !isNumeric(num2)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return (convertDouble(num1) + convertDouble(num2)) / 2;
    }

    @GetMapping(value = "/sqrt/{num1}")
    public Double sqrt (@PathVariable(value = "num1") String num1){
        if(!isNumeric(num1)){
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return Math.sqrt(convertDouble(num1));
    }

    private Double convertDouble(String num) {
        if(num == null) return 0D;
        String number = num.replaceAll(",",".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String num) {
        if(num == null) return false;
        String number = num.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
