package com.dw2.reserva.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DateTimeConfig {
    /**
     * Return a System {@link Clock} with zone UTC, useful for when a class needs to use the concept of "now" but the
     * result must be predictable in a unit/integration test.
     *
     * @return A UTC System Clock.
     */
    @Bean
    @ConditionalOnMissingBean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
