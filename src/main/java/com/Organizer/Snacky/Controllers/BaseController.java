package com.Organizer.Snacky.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

abstract class BaseController<T> {

    Authentication getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return null;
    }
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
