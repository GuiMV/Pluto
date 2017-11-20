package com.vieira.pluto.listener;

import com.vieira.pluto.thread.CancelamentoOrcamentoThread;
import com.vieira.pluto.thread.ClassificacaoThread;
import org.flywaydb.core.Flyway;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class InitializerListener implements ServletContextListener {

    private long UM_DIA_MILLIS = 1000 * 60 * 60 * 24;
    @Inject
    private ClassificacaoThread classificacaoThread;
    @Inject
    private CancelamentoOrcamentoThread cancelamentoOrcamentoThread;

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
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 1);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.DATE, 1);

            Date time = calendar.getTime();
            Timer timer = new Timer();
            timer.schedule(classificacaoThread, time, UM_DIA_MILLIS);
            timer.schedule(cancelamentoOrcamentoThread, time, UM_DIA_MILLIS);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    classificacaoThread.run();
                    cancelamentoOrcamentoThread.run();
                }
            }, 5 * 60 * 1000);

        } catch (Exception e) {
            System.out.println("Erro ao inicializar threads!");
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("--> Servlet listener destroyed");
    }

}
