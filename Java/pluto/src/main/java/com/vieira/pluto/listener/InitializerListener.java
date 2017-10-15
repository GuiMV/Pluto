/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
//import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author Guilherme
 */
@WebListener
public class InitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("--> Servlet listener invoked");

        try {
            // Create the Flyway instance
            Flyway flyway = new Flyway();

            // Point it to the database
            flyway.setDataSource("jdbc:postgresql://localhost:5432/pluto", "postgres", "123");

            // Start the migration
            flyway.migrate();
        } catch (Exception e) {
            System.out.println("Erro ao realizar migração do banco!");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("--> Servlet listener destroyed");
    }

}
