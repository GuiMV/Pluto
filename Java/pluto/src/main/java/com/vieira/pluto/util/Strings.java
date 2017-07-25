/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.util;

import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class Strings {    

    public static boolean validaCep(String cep) {
        return cep.matches("\\d{8}");
    }

    public static String apenasNumeros(String string) {
        if(Objects.isNull(string)){
            return null;
        }
        return string.replaceAll("\\D", "");
    }

    public static String formatarCep(String string) {
        if(Objects.isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        return string.replaceAll("(\\d{2})(\\d{3})(\\d+)", "$1.$2-$3");
    }

    public static String formatarCpf(String string) {
        if(Objects.isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        return string.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1.$2.$3-$4");
    }

    public static String formatarTelefone(String string) {
        if(Objects.isNull(string)){
            return null;
        }
        string = apenasNumeros(string);
        if(string.length() <= 9){
        return string.replaceAll("(\\d+)(\\d{4})", "$1-$2");            
        }
        return string.replaceAll("(\\d{2})(\\d+)(\\d{4})", "($1) $2-$3");
    }
}
