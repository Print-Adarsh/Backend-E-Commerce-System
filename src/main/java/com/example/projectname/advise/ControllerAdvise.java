package com.example.projectname.advise;

import com.example.projectname.dto.ErrorDTO;
import com.example.projectname.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerAdvise {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("400");
        errorDTO.setErrorMsg("Bad Request");

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("404");
        errorDTO.setErrorMsg("Product Not found");

        return ResponseEntity.badRequest().body(errorDTO);
    }

}
