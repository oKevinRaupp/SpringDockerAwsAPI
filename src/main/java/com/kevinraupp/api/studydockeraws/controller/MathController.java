package com.kevinraupp.api.studydockeraws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @GetMapping(value = "/sum/{num1}/{num2}")
    public Double sum(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2){
        if(!isNumeric(num1) || !isNumeric(num2)){
            throw new RuntimeException();
        }
        return convertDouble(num1) + convertDouble(num2);
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
