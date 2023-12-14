package com.kevinraupp.api.studydockeraws.controller;

import com.kevinraupp.api.studydockeraws.converters.NumberConverter;
import com.kevinraupp.api.studydockeraws.data.vo.v1.PersonVO;
import com.kevinraupp.api.studydockeraws.exceptions.UnsuportedMathOperationException;
import com.kevinraupp.api.studydockeraws.math.SimpleMath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/math/v1")
@Tag(name = "Math", description = "Endpoint for simple math exercises.")
public class MathController {
    private SimpleMath math = new SimpleMath();

    @GetMapping(value = "/sum/{num1}/{num2}")
    @Operation(summary = "Sum two numbers!", description = "Sum two numbers!", tags = {"Math"},
            responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public Double sum(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2) {
        if (!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.sum(NumberConverter.convertDouble(num1), NumberConverter.convertDouble(num2));
    }

    @GetMapping(value = "/sub/{num1}/{num2}")
    @Operation(summary = "Subtract two numbers!", description = "Subtract two numbers!", tags = {"Math"},
            responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public Double sub(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2) {
        if (!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.sub(NumberConverter.convertDouble(num1), NumberConverter.convertDouble(num2));
    }

    @GetMapping(value = "/div/{num1}/{num2}")
    @Operation(summary = "Divide two numbers!", description = "Divide two numbers!", tags = {"Math"},
            responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public Double div(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2) {
        if (!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.div(NumberConverter.convertDouble(num1), NumberConverter.convertDouble(num2));
    }

    @GetMapping(value = "/mult/{num1}/{num2}")
    @Operation(summary = "Multiply two numbers!", description = "Multiply two numbers!", tags = {"Math"},
            responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public Double mult(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2) {
        if (!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.mult(NumberConverter.convertDouble(num1), NumberConverter.convertDouble(num2));
    }

    @GetMapping(value = "/average/{num1}/{num2}")
    @Operation(summary = "Average of two numbers!", description = "Average of two numbers!", tags = {"Math"},
            responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public Double average(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2) {
        if (!NumberConverter.isNumeric(num1) || !NumberConverter.isNumeric(num2)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.average(NumberConverter.convertDouble(num1), NumberConverter.convertDouble(num2));
    }

    @GetMapping(value = "/sqrt/{num1}")
    @Operation(summary = "Square root of a number!", description = "Square root of a number!", tags = {"Math"},
            responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public Double sqrt(@PathVariable(value = "num1") String num1) {
        if (!NumberConverter.isNumeric(num1)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!! :) ");
        }
        return math.sqrt(NumberConverter.convertDouble(num1));
    }
}
