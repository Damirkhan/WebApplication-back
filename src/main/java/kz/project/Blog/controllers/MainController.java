package kz.project.Blog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MainController {

    protected ResponseEntity<?> buildResponse(Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(data, httpStatus);
    }

    protected ResponseEntity<?> buildSuccessResponse(Object data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
