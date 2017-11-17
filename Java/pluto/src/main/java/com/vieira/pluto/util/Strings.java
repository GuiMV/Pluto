/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.util;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 * @author Guilherme
 */
public class Strings {    

    public static boolean validaCep(String cep) {
        return cep.matches("\\d{8}");
    }

    public static String apenasNumeros(String string) {
        if(isNull(string)){
            return null;
        }
        return string.replaceAll("\\D", "");
    }

    public static String formatarCep(String string) {
        if(isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        return string.replaceAll("(\\d{2})(\\d{3})(\\d+)", "$1.$2-$3");
    }

    public static String formatarCpf(String string) {
        if(isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        return string.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1.$2.$3-$4");
    }

    public static String formatarCnpj(String string) {
        if(isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        return string.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d+)", "$1.$2.$3/$4-$5");
    }

    public static String formatarCpfCnpj(String string) {
        if(isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        if (string.length() == 11){
            return formatarCpf(string);
        }
        return formatarCnpj(string);
    }

    public static String formatarTelefone(String string) {
        if(isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        if(string.length() <= 9){
        return string.replaceAll("(\\d+)(\\d{4})", "$1-$2");            
        }
        return string.replaceAll("(\\d{2})(\\d+)(\\d{4})", "($1) $2-$3");
    }

    public static boolean isNullOrEmpty(String string){
        return  isNull(string) || string.trim().isEmpty();
    }

    public static boolean IsNonNullAndNonEmpty(String string){
        return  nonNull(string) && !string.trim().isEmpty();
    }
}
