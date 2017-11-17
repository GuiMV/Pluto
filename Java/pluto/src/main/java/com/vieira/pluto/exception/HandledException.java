/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.exception;

/**
 *
 * @author Guilherme
 */
public class HandledException extends RuntimeException{

    public HandledException(String message) {
        super(message);
    }

    public HandledException(String message, Throwable cause) {
        super(message, cause);
    }
}
