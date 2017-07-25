/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.ws.client;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme
 */
public class ViaCEPClientTest {

//    @Test
    public void testGetCepResponse() {
        ViaCEPClient.consultaCEP("88134510");
    }
    
}
