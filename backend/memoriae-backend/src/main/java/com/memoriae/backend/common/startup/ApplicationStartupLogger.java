package com.memoriae.backend.common.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationStartupLogger {

    private static final Logger log =
            LoggerFactory.getLogger(ApplicationStartupLogger.class);

    private final Environment environment;

    public ApplicationStartupLogger(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logApplicationStartup() {

        String applicationName =
                environment.getProperty(
                        "spring.application.name",
                        "unknown"
                );

        String serverPort =
                environment.getProperty(
                        "server.port",
                        "8080"
                );

        String databaseUrl =
                environment.getProperty(
                        "spring.datasource.url",
                        "not configured"
                );

        String databaseUser =
                environment.getProperty(
                        "spring.datasource.username",
                        "not configured"
                );

        String mailHost =
                environment.getProperty(
                        "spring.mail.host",
                        "not configured"
                );

        String mailPort =
                environment.getProperty(
                        "spring.mail.port",
                        "not configured"
                );

        String mailFrom =
                environment.getProperty(
                        "app.mail.from-address",
                        "not configured"
                );

        String[] activeProfiles =
                environment.getActiveProfiles();

        String profiles =
                activeProfiles.length == 0
                        ? "default"
                        : Arrays.toString(activeProfiles);

        log.info("""
                
                ============================================================
                  MEMORIAE STARTED SUCCESSFULLY
                ============================================================
                  Application : {}
                  Profiles    : {}
                  HTTP Port   : {}
                
                  Database
                  --------
                  URL         : {}
                  Username    : {}
                
                  Mail
                  ----
                  SMTP Host   : {}
                  SMTP Port   : {}
                  From        : {}
                
                  Local API   : http://localhost:{}
                ============================================================
                """,
                applicationName,
                profiles,
                serverPort,
                databaseUrl,
                databaseUser,
                mailHost,
                mailPort,
                mailFrom,
                serverPort
        );
    }
}