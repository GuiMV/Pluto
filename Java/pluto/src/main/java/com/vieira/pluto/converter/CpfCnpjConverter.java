/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.converter;

import com.vieira.pluto.util.Strings;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Guilherme
 */
@FacesConverter("converter.CpfCnpjConverter")
public class CpfCnpjConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Strings.apenasNumeros(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return Strings.formatarCpfCnpj(String.class.cast(value));
    }

}
