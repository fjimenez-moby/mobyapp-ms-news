package com.mobydigital.academy.news.exception;

import com.mobydigital.academy.news.controller.WebhookController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(RuntimeException.class)
    @ApiResponse(
            responseCode = "500",
            description = "Error inesperado durante el procesamiento del webhook",
            content = @Content(
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(value = "Error procesando el webhook: mensaje de error detallado")
            )
    )
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        logger.severe("Error procesando webhook: " + ex.getMessage());

        logger.severe("StackTrace: " + Arrays.toString(ex.getStackTrace()));

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error procesando el webhook: " + ex.getMessage());
    }

}
