package com.workintech.s19d1.util;

import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class HollywoodValidation {
    public static void isMovieExist(Long id){
        throw new ApiException(id +"Movie is not found", HttpStatus.NOT_FOUND);
    }

    public static void isActorExist(Long id){
        throw new ApiException(id +"Movie is not found", HttpStatus.NOT_FOUND);
    }
}
