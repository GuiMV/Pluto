/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.converter;

import com.vieira.pluto.enums.Mes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import static java.util.Objects.isNull;

/**
 *
 * @author Guilherme
 */
@FacesConverter("converter.MesConverter")
public class MesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Mes mes = Mes.getByLabel(value);
        return isNull(mes) ? null : mes.getValue();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Mes mes = Mes.getByValue(Integer.class.cast(value));
        return isNull(mes) ? null : mes.getLabel();
    }

}
