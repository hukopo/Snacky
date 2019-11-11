package com.Organizer.Snacky.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

abstract class BaseController<T> {

    ResponseEntity notFound(T body) {
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    ResponseEntity<T> badRequest(T body) {
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    ResponseEntity<T> unauthorized(T body) {
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    ResponseEntity<T> ok(T body) {
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }


}
